# VITyarthi-prject
SmartSpend solves the real-world problem of unorganized personal finances. Unlike complex banking apps, this tool focuses on simplicity and speed, running entirely in the terminal. It features secure login and data persistence, allowing users to save their financial records locally without a database
# 💰 SmartSpend - Personal Finance Tracker

> A robust, console-based Java application designed to help users track expenses, manage budgets, and analyze spending habits efficiently.

![Java](https://img.shields.io/badge/Language-Java-orange) ![Type](https://img.shields.io/badge/Type-Console_Application-blue) ![Status](https://img.shields.io/badge/Status-Completed-green)

## 📖 Project Overview
**SmartSpend** is a personal finance management tool developed as part of the *Flipped Course Evaluation*. It addresses the real-world problem of unorganized spending by providing a simple, digital solution to record and monitor daily expenses.

Unlike complex banking apps, SmartSpend focuses on simplicity and speed, running entirely in the terminal with a clean, menu-driven interface. It ensures data persistence using file handling, allowing users to save their financial data locally.

## 🚀 Key Features
This project implements three major functional modules:

### 1. 🔐 User Management (Security)
* **Registration:** New users can create an account with a unique username and password.
* **Authentication:** Secure login system prevents unauthorized access to financial data.
* **Session Management:** Ensures data is loaded specifically for the logged-in user.

### 2. 💸 Transaction Management (CRUD)
* **Add Expense:** Users can record expenses with categories (Food, Travel, Bills), amounts, and dates.
* **View History:** Display a chronological list of all transactions.
* **Edit/Delete:** Users can modify incorrect entries or remove outdated records.
* **Input Validation:** Prevents invalid data (e.g., negative amounts or future dates).

### 3. 📊 Reporting & Analytics
* **Category Analysis:** Automatically calculates total spending per category (e.g., "Total spent on Food: $150").
* **Budget Alerts:** Warns the user if their total spending exceeds a predefined budget limit.

## 🛠️ Technology Stack
* **Language:** Java (JDK 17+)
* **User Interface:** Command Line Interface (CLI) / Console
* **Persistence:** Java File I/O (Serialization / CSV)
* **Architecture:** Modular OOP Design (Model-Service-Controller pattern)
* **Tools:** Git, GitHub, IntelliJ IDEA / VS Code

## 📂 Project Structure
The code follows a modular structure to ensure maintainability and scalability:

```text
SmartSpend/
├── src/
│   ├── Main.java              # Application Entry Point (Menu Loop)
│   ├── model/                 # Data Classes (User, Expense)
│   ├── service/               # Business Logic (UserManager, ExpenseManager)
│   └── util/                  # Utilities (Validation, Date Formatting)
├── data/                      # Stores .ser or .csv files (Persistence layer)
├── documentation/             # UML Diagrams and Reports
├── README.md                  # Project Documentation
└── statement.md               # Problem Statement & Scope
