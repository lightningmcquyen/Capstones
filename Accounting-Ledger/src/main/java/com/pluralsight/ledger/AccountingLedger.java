package com.pluralsight.ledger;

import java.util.Scanner;

public class AccountingLedger {
    private TransactionManager transactionManager;
    private Scanner scanley; // Scanner instance for user input

    public void run() {
        showHomeScreen(); // Display the home screen
        scanley.close(); // Close the scanner when done
    }

    public AccountingLedger() {
        transactionManager = new TransactionManager(); // Initialize TransactionManager
        scanley = new Scanner(System.in); // Create a new Scanner instance
    }



    public static void main(String[] args) {
        AccountingLedger app = new AccountingLedger();
        app.run(); // Run the application
    }



    // Display the home screen menu
    private void showHomeScreen() {
        String choice;

        do {
            System.out.println("$$$ Accounting Ledger Application $$$");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger Entries");
            System.out.println("R) Access Reports");
            System.out.println("X) Exit");

            choice = scanley.nextLine().toUpperCase(); // Read user choice

            switch (choice) {
                case "D":
                    transactionManager.addDepositPrompt(scanley); // Delegate to TransactionManager
                    break;
                case "P":
                    transactionManager.addPaymentPrompt(scanley); // Delegate to TransactionManager
                    break;
                case "L":
                    showLedgerEntries(); // Call the method to show ledger entries
                    break;
                case "R":
                    showReportsScreen(); // Call the method to show reports screen
                    break;
                case "X":
                    System.out.println("Exiting..."); // Exit the application
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        } while (!choice.equals("X")); // Continue until the user chooses to exit
    }

    // Method to display ledger entries
    private void showLedgerEntries() {
        String choice;

        do {
            System.out.println("Ledger Entries");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("H) Home");
            System.out.println("X) Exit");

            choice = scanley.nextLine().toUpperCase(); // Read user choice

            switch (choice) {
                case "A":
                    transactionManager.displayAllTransactions(); // Show all transactions
                    break;
                case "D":
                    transactionManager.displayDeposits(); // Show only deposits
                    break;
                case "P":
                    transactionManager.displayPayments(); // Show only payments
                    break;
                case "H":
                    return; // Return to home menu
                case "X":
                    System.out.println("Exiting..."); // Exit the application
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        } while (!choice.equals("X")); // Continue until the user chooses to exit
    }

    // Method to show the reports screen
    private void showReportsScreen() {
        String choice;

        do {
            System.out.println("Access Reports By:");
            System.out.println("(1) Month to Date");
            System.out.println("(2) Previous Month");
            System.out.println("(3) Year to Date");
            System.out.println("(4) Previous Year");
            System.out.println("(5) Search by Vendor");
            System.out.println("(H) Return Home");
            System.out.println("(X) Exit");

            choice = scanley.nextLine().toUpperCase(); // Read user choice

            switch (choice) {
                case "1":
                    transactionManager.showMonthToDateReport(); // Call method for Month to Date report
                    break;
                case "2":
                    transactionManager.showPreviousMonthReport(); // Call method for Previous Month report
                    break;
                case "3":
                    transactionManager.showYearToDateReport(); // Call method for Year to Date report
                    break;
                case "4":
                    transactionManager.showPreviousYearReport(); // Call method for Previous Year report
                    break;
                case "5":
                    transactionManager.searchByVendor(scanley); // Delegate vendor search to TransactionManager
                    break;
                case "H":
                    return; // Return to home menu
                case "X":
                    System.out.println("Exiting..."); // Exit the application
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        } while (!choice.equals("X")); // Continue until the user chooses to exit
    }
}