package com.pluralsight.ledger;

import java.io.*; // Imports classes for input and output (e.g., FileReader, BufferedReader, FileWriter)
import java.time.LocalDate; // Imports LocalDate for handling dates
import java.time.LocalTime; // Imports LocalTime for handling times
import java.time.format.DateTimeFormatter; // Imports DateTimeFormatter for parsing dates and times
import java.util.ArrayList; // Imports ArrayList for using dynamic arrays
import java.util.List; // Imports List interface

public class FileManager {
    private static final String FILE_PATH = "src/main/resources/transactions.csv"; // Path to the transactions file

    // Method to load transactions from the file
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>(); // List to hold transactions
        try (BufferedReader buffy = new BufferedReader(new FileReader(FILE_PATH))) { // BufferedReader for efficient reading
            String line;
            // Skip the header
            buffy.readLine();
            while ((line = buffy.readLine()) != null) { // Read each line of the file
                String[] parts = line.split("\\|"); // Split the line by '|'
                if (parts.length == 5) { // Ensure there are 5 parts
                    LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ISO_LOCAL_DATE); // Parse date
                    LocalTime time = LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HH:mm:ss")); // Parse time
                    String description = parts[2]; // Description
                    String vendor = parts[3]; // Vendor
                    double amount = Double.parseDouble(parts[4]); // Amount
                    transactions.add(new Transaction(date, time, description, vendor, amount)); // Create and add transaction
                }
            }
        } catch (IOException e) { // Handle IO exceptions
            e.printStackTrace();
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
        return transactions; // Return the list of transactions
    }

    // Method to save a transaction to the file
    public void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) { // Open file
            // Create a time formatter to format LocalTime without nanoseconds
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = transaction.getTime().format(timeFormatter); // Format the time

            writer.write(String.format("%s|%s|%s|%s|%.2f",
                    transaction.getDate(), formattedTime, transaction.getDescription(),
                    transaction.getVendor(), transaction.getAmount())); // Write transaction
            writer.newLine(); // Move to the next line
        } catch (IOException e) { // Handle IO exceptions
            e.printStackTrace();
            System.out.println("Error writing to transactions file: " + e.getMessage());
        }
    }
}