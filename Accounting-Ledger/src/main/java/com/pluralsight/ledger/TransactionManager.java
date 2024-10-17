package com.pluralsight.ledger;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;
    private FileManager fileManager;

    public TransactionManager() {
        transactions = new ArrayList<>();
        fileManager = new FileManager();
        loadTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        fileManager.saveTransaction(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> filterDeposits() {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        return deposits;
    }

    public List<Transaction> filterPayments() {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction);
            }
        }
        return payments;
    }

    public void displayTransactions(List<Transaction> transactionsToDisplay) {
        for (Transaction transaction : transactionsToDisplay) {
            System.out.println(transaction);
        }
    }

    private void loadTransactions() {
        transactions.addAll(fileManager.loadTransactions());
    }
}