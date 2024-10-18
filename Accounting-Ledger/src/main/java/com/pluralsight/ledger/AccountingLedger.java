package com.pluralsight.ledger;

import java.util.Scanner;


public class AccountingLedger {
    final private TransactionManager transactionManager;
    final private Scanner scanley; // Scanner instance for user input

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
            // ANSI escape code for green text
            String greenText = "\u001B[32m";
            String resetText = "\u001B[0m";

            System.out.println(greenText + """
                    💲 Accounting Ledger Application 💲
                    =====================================
                    (D) Add Deposit
                    (P) Make Payment
                    (L) Ledger Entries
                    (R) Access Reports
                    (X) Exit
                    """ + resetText);

            choice = scanley.nextLine().toUpperCase(); // Read user choice

            switch (choice) {
                case "D" -> transactionManager.addDepositPrompt(scanley); // Delegate to TransactionManager
                case "P" -> transactionManager.addPaymentPrompt(scanley); // Delegate to TransactionManager
                case "L" -> showLedgerEntries(); // Call the method to show ledger entries
                case "R" -> showReportsScreen(); // Call the method to show reports screen
                case "X" -> System.out.println("Exiting...\uD83D\uDC4B(•◡•)"); // Exit the application
                default -> System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        } while (!choice.equals("X")); // Continue until the user chooses to exit
    }

    // Method to display ledger entries
    private void showLedgerEntries() {
        String choice;

        do {
            // ANSI escape code for blue text
            String blueText = "\u001B[34m";
            String resetText = "\u001B[0m";

            System.out.println(blueText + """
                    💲 Ledger Entries 💲
                    ======================
                    (A) All Entries
                    (D) Deposits Only
                    (P) Payments Only
                    (H) Home
                    (X) Exit
                    """ + resetText);


            choice = scanley.nextLine().toUpperCase(); // Read user choice

            switch (choice) {
                case "A" -> transactionManager.displayAllTransactions(); // Show all transactions
                case "D" -> transactionManager.displayDeposits(); // Show only deposits
                case "P" -> transactionManager.displayPayments(); // Show only payments
                case "H" -> {
                    return; // Return to home menu
                }
                case "X" -> System.out.println("Exiting...\uD83D\uDC4B(•◡•)"); // Exit the application
                default -> System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        } while (!choice.equals("X")); // Continue until the user chooses to exit
    }

    // Method to show the reports screen
    private void showReportsScreen() {
        String choice;

        do { // ANSI escape code for magenta text
            String magentaText = "\u001B[35m";
            String resetText = "\u001B[0m";

            System.out.println(magentaText + """
                    💲 Transaction Reports 💲
                    ===========================
                    Generate Reports By:
                    (1) Month to Date
                    (2) Previous Month
                    (3) Year to Date
                    (4) Previous Year
                    (5) Search by Vendor
                    (H) Return Home
                    (X) Exit
                    """ + resetText);


            choice = scanley.nextLine().toUpperCase(); // Read user choice

            switch (choice) {
                case "1" -> transactionManager.showMonthToDateReport(); // Call method for Month to Date report
                case "2" -> transactionManager.showPreviousMonthReport(); // Call method for Previous Month report
                case "3" -> transactionManager.showYearToDateReport(); // Call method for Year to Date report
                case "4" -> transactionManager.showPreviousYearReport(); // Call method for Previous Year report
                case "5" -> transactionManager.searchByVendor(scanley); // Delegate vendor search to TransactionManager
                case "H" -> {
                    return; // Return to home menu
                }
                case "X" -> System.out.println("Exiting...\uD83D\uDC4B(•◡•)"); // Exit the application
                default -> System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        } while (!choice.equals("X")); // Continue until the user chooses to exit
    }
}