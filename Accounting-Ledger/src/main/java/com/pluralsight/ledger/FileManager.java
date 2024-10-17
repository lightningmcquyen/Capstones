package com.pluralsight.ledger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "src/main/resources/transactions.csv"; // Updated file path
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void saveTransaction(Transaction transaction) {
        try (BufferedWriter buffy = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = transaction.getDateTime().format(FORMATTER) + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount();
            buffy.write(line);
            buffy.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                LocalDateTime dateTime = LocalDateTime.parse(parts[0], FORMATTER);
                String description = parts[1];
                String vendor = parts[2];
                double amount = Double.parseDouble(parts[3]);
                transactions.add(new Transaction(dateTime, description, vendor, amount));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return transactions;
    }
}