package com.pluralsight.ledger;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AccountingLedger {
    private TransactionManager transactionManager;
    private Scanner scanley; // Renamed scanner to scanley

    public static void main(String[] args) {
        AccountingLedger app = new AccountingLedger();
        app.run();
    }

    public AccountingLedger() {
        transactionManager = new TransactionManager();
        scanley = new Scanner(System.in); // Updated initialization
    }

    public void run() {
        showHomeScreen();
        scanley.close(); // Updated to close scanley
    }

    private void showHomeScreen() {
        String choice;

        do {
            System.out.println("Home Screen");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger Entries");
            System.out.println("R) Access Reports");
            System.out.println("X) Exit");

            choice = scanley.nextLine().toUpperCase(); // Updated to use scanley

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    showLedgerEntries();
                    break;
                case "R":
                    showReportsScreen();
                    break;
                case "X":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private void addDeposit() {
        System.out.print("Enter description: ");
        String description = scanley.nextLine(); // Updated to use scanley
        System.out.print("Enter vendor: ");
        String vendor = scanley.nextLine(); // Updated to use scanley
        System.out.print("Enter amount: ");

        double amount = readAmount();
        LocalDateTime timestamp = LocalDateTime.now(); // Use .now() for current timestamp
        Transaction depositTransaction = new Transaction(timestamp, description, vendor, amount);
        transactionManager.addTransaction(depositTransaction);
    }

    private void makePayment() {
        System.out.print("Enter description: ");
        String description = scanley.nextLine(); // Updated to use scanley
        System.out.print("Enter vendor: ");
        String vendor = scanley.nextLine(); // Updated to use scanley
        System.out.print("Enter amount: ");

        double amount = readAmount();
        LocalDateTime timestamp = LocalDateTime.now(); // Use .now() for current timestamp
        Transaction paymentTransaction = new Transaction(timestamp, description, vendor, -amount);
        transactionManager.addTransaction(paymentTransaction);
    }

    private void showLedgerEntries() {
        String choice;

        do {
            System.out.println("Ledger Entries");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("H) Home");
            System.out.println("X) Exit");

            choice = scanley.nextLine().toUpperCase(); // Updated to use scanley

            switch (choice) {
                case "A":
                    transactionManager.displayTransactions(transactionManager.getAllTransactions());
                    break;
                case "D":
                    transactionManager.displayTransactions(transactionManager.filterDeposits());
                    break;
                case "P":
                    transactionManager.displayTransactions(transactionManager.filterPayments());
                    break;
                case "H":
                    return; // Go back to the home menu
                case "X":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private void showReportsScreen() {
        String choice;

        do {
            System.out.println("Reports Screen");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("H) Home");
            System.out.println("X) Exit");

            choice = scanley.nextLine().toUpperCase(); // Updated to use scanley

            switch (choice) {
                case "1":
                    // Implement Month to Date report
                    System.out.println("Generating Month to Date report...");
                    break;
                case "2":
                    // Implement Previous Month report
                    System.out.println("Generating Previous Month report...");
                    break;
                case "3":
                    // Implement Year to Date report
                    System.out.println("Generating Year to Date report...");
                    break;
                case "4":
                    // Implement Previous Year report
                    System.out.println("Generating Previous Year report...");
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanley.nextLine(); // Updated to use scanley
                    // Implement search by vendor
                    System.out.println("Searching for transactions by vendor: " + vendor);
                    break;
                case "H":
                    return; // Go back to the home menu
                case "X":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private double readAmount() {
        double amount = 0;
        boolean valid = false;

        while (!valid) {
            try {
                amount = Double.parseDouble(scanley.nextLine()); // Updated to use scanley
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Please enter again: ");
            }
        }
        return amount;
    }
}