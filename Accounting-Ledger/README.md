# Accounting Ledger Application

## Overview

The Accounting Ledger Application is a console-based Java application designed for managing financial transactions. Users can add deposits and payments, view ledger entries, and generate various reports to track their finances efficiently.

## Features

- **Add Deposits**: Record income with a description and vendor.
- **Make Payments**: Log expenses with details about the transaction.
- **View Ledger Entries**: Display all transactions, deposits only, or payments only.
- **Generate Reports**: Access month-to-date, previous month, year-to-date, and previous year reports.
- **Search Transactions**: Find transactions by vendor.

## Screenshots

### Home Screen
![Home Screen](path/to/home_screen_screenshot.png)

### Ledger Entries
![Ledger Entries](path/to/ledger_entries_screenshot.png)

### Reports Menu
![Reports Menu](path/to/reports_menu_screenshot.png)

## FileWriter Class

The `FileWriter` class is a crucial part of the application, responsible for persisting transaction data to a CSV file. This allows users to save their financial records and retrieve them later. The application uses a custom `FileManager` class that interacts with the `FileWriter` to handle file operations.

### Key Methods in FileManager

- **loadTransactions()**: Reads transactions from the specified CSV file, creating `Transaction` objects and adding them to the transaction list.

- **saveTransaction(Transaction transaction)**: Appends a new transaction to the CSV file. Each transaction is formatted and written in a structured way to ensure data consistency.


