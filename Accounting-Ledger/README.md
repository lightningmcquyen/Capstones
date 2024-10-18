# 💰 Accounting Ledger Application 💲

## Overview

The Accounting Ledger Application is a Java application designed for managing financial transactions. Users can add deposits and payments, view ledger entries, and generate various reports to track their finances efficiently.

## Features

- **Add Deposits**: Record income with a description and vendor.
- **Make Payments**: Log expenses with details about the transaction.
- **View Ledger Entries**: Display all transactions, deposits only, or payments only.
- **Generate Reports**: Access month-to-date, previous month, year-to-date, and previous year reports.
- **Search Transactions**: Find transactions by vendor.




## Menus

### Home Screen
![Accounting Ledger Application](images/Home.png)

### Ledger Menu
![Ledger Entries](images/LedgerScreen.png)

### Reports Menu
![Transaction Reports](images/ReportScreen.png)



## My FileManager Class 💖

### Key Methods in FileManager

- **loadTransactions()**: Reads transactions from the specified CSV file, creating `Transaction` objects and adding them to the transaction list.

- **saveTransaction(Transaction transaction)**: Appends a new transaction to the CSV file. Each transaction is formatted and written in a structured way to ensure data consistency.


