package com.pluralsight.ledger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final LocalDate date; // Date of the transaction
    private final LocalTime time; // Time of the transaction
    private final String description; // Description of the transaction
    private final String vendor; // Vendor associated with the transaction
    private final double amount; // Amount of the transaction

    // Define formatters
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Constructor to initialize a new transaction
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getters for each field
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // Methods to get formatted date and time
    public String getFormattedDate() {
        return date.format(DATE_FORMATTER);
    }

    public String getFormattedTime() {
        return time.format(TIME_FORMATTER);
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %.2f",
                getFormattedDate(), getFormattedTime(), description, vendor, amount);
    }
}

