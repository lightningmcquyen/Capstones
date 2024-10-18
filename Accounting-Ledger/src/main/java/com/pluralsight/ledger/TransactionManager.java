package com.pluralsight.ledger;

import java.time.LocalDate; // Imports LocalDate for handling dates
import java.time.LocalTime; // Imports LocalTime for handling times
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; // Imports ArrayList for using dynamic arrays
import java.util.List; // Imports List interface
import java.util.Scanner; // Imports Scanner for user input

public class TransactionManager {
    private List<Transaction> transactionManager; // List to hold all transactions
    private FileManager fileManager; // Instance of FileManager for file operations

    // Method to load transactions from the file
    private void loadTransactions() {
        transactionManager.addAll(fileManager.loadTransactions()); // Load transactions and add to the list
    }

    // Constructor initializes the transaction list and loads transactions from file
    public TransactionManager() {
        transactionManager = new ArrayList<>(); // Initialize the transactions list
        fileManager = new FileManager(); // Create a new FileManager instance
        loadTransactions(); // Load existing transactions from the file
    }

    // Method to read amount input
    private double readAmount(Scanner scanley) {
        while (true) {
            try {
                String input = scanley.nextLine(); // Read input from user
                return Double.parseDouble(input); // Try to parse as double
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Invalid amount. Please enter a valid number."); // Handle invalid input
            }
        }
    }

    // Method to add a deposit
    private void addDeposit(String description, String vendor, double amount) {
        LocalDate date = LocalDate.now(); // Get current date
        LocalTime time = LocalTime.now(); // Get current time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);
        Transaction depositTransaction = new Transaction(date, time, description, vendor, amount); // Create transaction
        transactionManager.add(depositTransaction); // Add transaction to the list
        fileManager.saveTransaction(depositTransaction); // Save transaction to the file
    }

    // Method to add a deposit
    public void addDepositPrompt(Scanner scanley) {
        System.out.print("Enter transaction description: ");
        String description = scanley.nextLine(); // Read description
        System.out.print("Enter vendor: ");
        String vendor = scanley.nextLine(); // Read vendor
        System.out.print("Enter deposit amount: ");
        double amount = readAmount(scanley); // Read amount using a helper method
        addDeposit(description, vendor, amount); // Delegate to addDeposit method
        System.out.println("Deposit successful.");
        System.out.println("What would you like to do next?");
    }

    // Method to add a payment
    private void addPayment(String description, String vendor, double amount) {
        LocalDate date = LocalDate.now(); // Get current date
        LocalTime time = LocalTime.now(); // Get current time
        Transaction paymentTransaction = new Transaction(date, time, description, vendor, -amount); // Create payment transaction
        transactionManager.add(paymentTransaction); // Add transaction to the list
        fileManager.saveTransaction(paymentTransaction); // Save transaction to the file
    }


    // Method to add a payment
    public void addPaymentPrompt(Scanner scanley) {
        System.out.print("Enter item description: ");
        String description = scanley.nextLine(); // Read description
        System.out.print("Enter vendor: ");
        String vendor = scanley.nextLine(); // Read vendor
        System.out.print("Enter payment amount: ");
        double amount = readAmount(scanley); // Read amount using a helper method
        addPayment(description, vendor, amount); // Delegate to addPayment method
        System.out.println("Payment successful.");
        System.out.println("What would you like to do next?");
    }

    // Method to display a list of transactions
    private void displayTransactions(List<Transaction> transactionsToDisplay) {
        if (transactionsToDisplay.isEmpty()) {
            System.out.println("No transactions found."); // Inform user if no transactions exist
            return;
        }
        for (Transaction transaction : transactionsToDisplay) {
            System.out.println(transaction);
        }// Display each transaction
    }

    // Method to display all transactions
    public void displayAllTransactions() {
        displayTransactions(transactionManager); // Show all transactions
    }


    // Method to filter and display only deposit transactions
    public void displayDeposits() {
        List<Transaction> deposits = filterDeposits(); // Get deposits
        displayTransactions(deposits); // Display deposits
    }

    // Method to filter and display only payment transactions
    public void displayPayments() {
        List<Transaction> payments = filterPayments(); // Get payments
        displayTransactions(payments); // Display payments
    }

    // Method to filter and return only deposit transactions
    private List<Transaction> filterDeposits() {
        List<Transaction> deposits = new ArrayList<>(); // List to hold deposits
        for (Transaction transaction : transactionManager) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction); // Add deposit transactions to the list
            }
        }
        return deposits; // Return the list of deposits
    }

    // Method to filter and return only payment transactions
    private List<Transaction> filterPayments() {
        List<Transaction> payments = new ArrayList<>(); // List to hold payments
        for (Transaction transaction : transactionManager) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction); // Add payment transactions to the list
            }
        }
        return payments; // Return the list of payments
    }


    // Placeholder methods for reports
    public void showMonthToDateReport() {
        // Implement the month-to-date report logic here
        LocalDate today = LocalDate.now();
        for (Transaction transaction : transactionManager) {
            if (transaction.getDate().getMonth() == today.getMonth() &&
                    transaction.getDate().getYear() == today.getYear()) {
                System.out.println(transaction);
            }
        }
    }

    public void showPreviousMonthReport() {
        // Implement the previous month report logic here
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth());

        for (Transaction transaction : transactionManager) {
            if (transaction.getDate().isEqual(firstDayOfLastMonth) ||
                    (transaction.getDate().isAfter(firstDayOfLastMonth) && transaction.getDate().isBefore(lastDayOfLastMonth.plusDays(1)))) {
                System.out.println(transaction);
            }
        }
    }

    public void showYearToDateReport() {
        // Implement the year-to-date report logic here
        LocalDate today = LocalDate.now();
        for (Transaction transaction : transactionManager) {
            if (transaction.getDate().getYear() == today.getYear()) {
                System.out.println(transaction);
            }
        }
    }

    public void showPreviousYearReport() {
        // Implement the previous year report logic here
        LocalDate today = LocalDate.now();
        for (Transaction transaction : transactionManager) {
            if (transaction.getDate().getYear() == today.getYear() - 1) {
                System.out.println(transaction);
            }
        }
    }

    public void searchByVendor(Scanner scanley) {
        // Implement the search by vendor logic here
        String vendor = scanley.nextLine().trim();
        for (Transaction transaction : transactionManager) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
            }
        }

    }
}