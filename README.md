
# 💰 SmartSpend: Personal Finance Tracker

**Author:** Vedansh Srivastava
**Course:** CSE2006
**Student ID:** 24BSA10303

---

## 1. 📖 Project Abstract
SmartSpend is a console-based Java application designed to solve the problem of unorganized personal finances. In a world of complex banking apps, this project offers a lightweight, privacy-focused solution for students to track daily expenses, categorize spending (e.g., Food, Travel), and monitor budget limits.

I built this project to demonstrate **Object-Oriented Programming (OOP)** principles, **Modular Architecture**, and **Java File Persistence** without relying on external databases.

---

## 2. 🏗️ System Architecture & Design
The project follows a **Service-Layer Architecture**, which separates the data (Model) from the logic (Service) and the user interaction (Main/View).

### 2.1 The Logic Flow
1.  **Input Layer:** The `Main` class acts as the controller. It displays the menu, accepts user input via `Scanner`, and passes it to the Service layer.
2.  **Service Layer:** `UserManager` and `ExpenseManager` handle the "heavy lifting"—validating passwords, calculating totals, and formatting dates.
3.  **Data Layer:** The `FileHandler` writes objects to `.csv` files, ensuring data persists after the program closes.
    <img width="1024" height="559" alt="image" src="https://github.com/user-attachments/assets/a922eb47-eccd-4fa6-ad86-3f3871827af3" />


### 2.2 Design Decisions
* **Why Console instead of GUI?** I focused on backend logic and algorithm efficiency over visual design. This allowed me to implement robust error handling and file I/O.
* **Why CSV/Text Files?** Since a SQL database setup was out of scope, I implemented a custom file parser. This mimics a database by reading data into `ArrayLists` at startup and writing updates back to the file immediately.
* **Authentication:** I used a simple Session Check. When a user logs in, their `User` object is stored in a `currentUser` variable. All expense queries are filtered by this User ID to ensure privacy.

---

## 3. 📂 Technical Code Details (Class Dictionary)

The codebase is organized into specific packages to meet the modularity requirement:

### 📦 Package: `model`
* **`User.java`**: A POJO (Plain Old Java Object) implementing `Serializable`.
    * *Fields:* `id`, `username`, `password`.
    * *Key Logic:* Overrides `equals()` to compare users by ID, not memory location.
* **`Expense.java`**: Represents a single financial transaction.
    * *Fields:* `expenseId`, `userId` (Foreign Key), `amount`, `category`, `date`.
    * *Key Logic:* Uses `LocalDate` for accurate date handling.

### 📦 Package: `service`
* **`UserManager.java`**:
    * *registerUser()*: Checks if a username exists before creating a new one.
    * *authenticate()*: Verifies credentials and returns a User object.
* **`ExpenseManager.java`**:
    * *addExpense()*: Appends a new record to the list and saves to file.
    * *getExpensesByUser()*: filters the global list to return only the logged-in user's data.
    * *generateReport()*: Aggregates costs by Category (e.g., sums all "Food" entries).

### 📦 Package: `util`
* **`InputValidator.java`**: Contains static methods to validate inputs (e.g., ensuring the Price > 0). This prevents the application from crashing due to `InputMismatchException`.

---

## 4. 💾 Data Storage (Persistence)
The application stores data in the `data/` directory:

1.  **`users.csv`**: Stores user credentials.
    * *Format:* `UUID,Username,Password`
2.  **`expenses.csv`**: Stores transaction history.
    * *Format:* `ExpenseID,UserID,Category,Amount,Date,Description`

*Note: If these files do not exist, the application automatically creates them on the first run.*

---

## 5. ⚙️ Setup & Execution Instructions

### Prerequisites
* **Java Development Kit (JDK):** Version 8 or higher.
* **Operating System:** Windows, macOS, or Linux.

### Step-by-Step Installation
1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/your-username/SmartSpend.git](https://github.com/your-username/SmartSpend.git)
    ```
2.  **Navigate to Directory:**
    ```bash
    cd SmartSpend
    ```
3.  **Compile the Source Code:**
    (Note: We compile all packages together to link dependencies)
    ```bash
    javac -d bin src/Main.java src/model/*.java src/service/*.java src/util/*.java
    ```
4.  **Run the Application:**
    ```bash
    java -cp bin Main
    ```

---

## 6. 🧪 Testing Scenario
To verify the functional requirements

1.  **Launch:** Run the app. You should see the "Welcome to SmartSpend" menu.
2.  **Register:** Select option `1`. Enter username `testUser` and password `123`.
    * *Check:* Console should say "Registration Successful".
3.  **Login:** Select option `2`. Login with the credentials above.
4.  **Add Expense:** Select "Add Expense".
    * *Input:* Category: `Food`, Amount: `500`, Date: `2023-10-25`.
5.  **Verify Persistence:** **Close the application completely.** Re-open it, login, and select "View Report".
    * *Result:* The $500 Food expense should still be visible.

---

## 7. 🔮 Future Enhancements
* **Encryption:** Hash passwords before saving to CSV for better security.
* **Export:** Add functionality to export the expense report as a PDF file.
***GUI:** Migrate the frontend to JavaFX or Swing.

---
