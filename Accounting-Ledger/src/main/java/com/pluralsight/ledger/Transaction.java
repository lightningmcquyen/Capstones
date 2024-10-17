package com.pluralsight.ledger;

import java.time.LocalDate; // Imports the LocalDate class for handling dates
import java.time.LocalTime; // Imports the LocalTime class for handling times

public class Transaction {
    private LocalDate date; // Date of the transaction
    private LocalTime time; // Time of the transaction
    private String description; // Description of the transaction
    private String vendor; // Vendor associated with the transaction
    private double amount; // Amount of the transaction

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

    // toString method for easy printing
    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %.2f", date, time, description, vendor, amount);
    }
}
