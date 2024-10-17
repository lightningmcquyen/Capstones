package com.pluralsight.ledger;

import java.time.LocalDate; // Imports LocalDate for handling dates
import java.time.LocalTime; // Imports LocalTime for handling times
import java.util.ArrayList; // Imports ArrayList for using dynamic arrays
import java.util.List; // Imports List interface
import java.util.Scanner; // Imports Scanner for user input

public class TransactionManager {
    private List<Transaction> transactions; // List to hold all transactions
    private FileManager fileManager; // Instance of FileManager for file operations

    // Constructor initializes the transaction list and loads transactions from file
    public TransactionManager() {
        transactions = new ArrayList<>(); // Initialize the transactions list
        fileManager = new FileManager(); // Create a new FileManager instance
        loadTransactions(); // Load existing transactions from the file
    }

    // Method to add a deposit
    public void addDepositPrompt(Scanner scanley) {
        System.out.print("Enter description: ");
        String description = scanley.nextLine(); // Read description
        System.out.print("Enter vendor: ");
        String vendor = scanley.nextLine(); // Read vendor
        System.out.print("Enter amount: ");
        double amount = readAmount(scanley); // Read amount using a helper method
        addDeposit(description, vendor, amount); // Delegate to addDeposit method
    }

    // Method to add a payment
    public void addPaymentPrompt(Scanner scanley) {
        System.out.print("Enter description: ");
        String description = scanley.nextLine(); // Read description
        System.out.print("Enter vendor: ");
        String vendor = scanley.nextLine(); // Read vendor
        System.out.print("Enter amount: ");
        double amount = readAmount(scanley); // Read amount using a helper method
        addPayment(description, vendor, amount); // Delegate to addPayment method
    }

    // Method to load transactions from the file
    private void loadTransactions() {
        transactions.addAll(fileManager.loadTransactions()); // Load transactions and add to the list
    }

    // Method to display all transactions
    public void displayAllTransactions() {
        displayTransactions(transactions); // Show all transactions
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
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction); // Add deposit transactions to the list
            }
        }
        return deposits; // Return the list of deposits
    }

    // Method to filter and return only payment transactions
    private List<Transaction> filterPayments() {
        List<Transaction> payments = new ArrayList<>(); // List to hold payments
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction); // Add payment transactions to the list
            }
        }
        return payments; // Return the list of payments
    }

    // Method to display a list of transactions
    private void displayTransactions(List<Transaction> transactionsToDisplay) {
        if (transactionsToDisplay.isEmpty()) {
            System.out.println("No transactions found."); // Inform user if no transactions exist
            return;
        }
        transactionsToDisplay.forEach(transaction -> System.out.println(transaction)); // Display each transaction
    }

    // Method to read and validate amount input
    private double readAmount(Scanner scanley) {
        while (true) {
            try {
                String input = scanley.nextLine(); // Read input from user
                return Double.parseDouble(input); // Try to parse as double
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number."); // Handle invalid input
            }
        }
    }

    // Method to add a deposit
    private void addDeposit(String description, String vendor, double amount) {
        LocalDate date = LocalDate.now(); // Get current date
        LocalTime time = LocalTime.now(); // Get current time
        Transaction depositTransaction = new Transaction(date, time, description, vendor, amount); // Create transaction
        transactions.add(depositTransaction); // Add transaction to the list
        fileManager.saveTransaction(depositTransaction); // Save transaction to the file
    }

    // Method to add a payment
    private void addPayment(String description, String vendor, double amount) {
        LocalDate date = LocalDate.now(); // Get current date
        LocalTime time = LocalTime.now(); // Get current time
        Transaction paymentTransaction = new Transaction(date, time, description, vendor, -amount); // Create payment transaction
        transactions.add(paymentTransaction); // Add transaction to the list
        fileManager.saveTransaction(paymentTransaction); // Save transaction to the file
    }

    // Placeholder methods for reports
    public void showMonthToDateReport() {
        // Implement the month-to-date report logic here
    }

    public void showPreviousMonthReport() {
        // Implement the previous month report logic here
    }

    public void showYearToDateReport() {
        // Implement the year-to-date report logic here
    }

    public void showPreviousYearReport() {
        // Implement the previous year report logic here
    }

    public void searchByVendor(Scanner scanley) {
        // Implement the search by vendor logic here
    }
}