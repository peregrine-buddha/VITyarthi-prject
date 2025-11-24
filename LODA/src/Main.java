import model.Expense;

import service.ExpenseManager;
import service.ReportService;
import service.UserManager;
import util.InputValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for the SmartSpend application.
 * Handles the main menu loop and user interaction.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = new UserManager();
    private static final ExpenseManager expenseManager = new ExpenseManager();
    private static final ReportService reportService = new ReportService();

    public static void main(String[] args) {
        System.out.println("Welcome to SmartSpend - Personal Finance Tracker");

        while (true) {
            if (userManager.getCurrentUser() == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    /**
     * Displays the authentication menu (Login/Register).
     */
    private static void showAuthMenu() {
        System.out.println("\n--- Auth Menu ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                performLogin();
                break;
            case "2":
                performRegistration();
                break;
            case "3":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Handles user login.
     */
    private static void performLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userManager.login(username, password)) {
            System.out.println("Login successful! Welcome, " + username + ".");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    /**
     * Handles user registration.
     */
    private static void performRegistration() {
        System.out.print("Enter desired username: ");
        String username = scanner.nextLine();
        if (!InputValidator.isValidString(username)) {
            System.out.println("Invalid username.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (!InputValidator.isValidString(password)) {
            System.out.println("Invalid password.");
            return;
        }

        if (userManager.register(username, password)) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    /**
     * Displays the main dashboard menu.
     */
    private static void showMainMenu() {
        System.out.println("\n--- Main Dashboard ---");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Edit Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. View Reports");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addExpense();
                break;
            case "2":
                viewExpenses();
                break;
            case "3":
                editExpense();
                break;
            case "4":
                deleteExpense();
                break;
            case "5":
                viewReports();
                break;
            case "6":
                userManager.logout();
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * UI flow for adding an expense.
     */
    private static void addExpense() {
        System.out.println("\n--- Add Expense ---");
        
        System.out.print("Enter Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate date = InputValidator.parseDate(dateStr);
        if (date == null) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.print("Enter Category (Food, Travel, etc.): ");
        String category = scanner.nextLine();
        if (!InputValidator.isValidString(category)) {
            System.out.println("Invalid category.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (!InputValidator.isPositiveAmount(amount)) {
                System.out.println("Amount must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            return;
        }

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        expenseManager.addExpense(userManager.getCurrentUser(), date, category, amount, description);
        System.out.println("Expense added successfully.");
    }

    /**
     * Displays all expenses for the current user.
     */
    private static void viewExpenses() {
        System.out.println("\n--- Your Expenses ---");
        List<Expense> expenses = expenseManager.getExpensesByUser(userManager.getCurrentUser());
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense e : expenses) {
                System.out.println(e);
            }
        }
    }

    /**
     * UI flow for editing an expense.
     */
    private static void editExpense() {
        viewExpenses();
        System.out.print("Enter ID of expense to edit: ");
        String id = scanner.nextLine();

        // For simplicity, we ask for all fields again. In a real app, we might allow partial updates.
        System.out.print("Enter New Date (yyyy-MM-dd): ");
        LocalDate date = InputValidator.parseDate(scanner.nextLine());
        if (date == null) {
            System.out.println("Invalid date.");
            return;
        }

        System.out.print("Enter New Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter New Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();

        if (expenseManager.editExpense(id, userManager.getCurrentUser(), date, category, amount, description)) {
            System.out.println("Expense updated.");
        } else {
            System.out.println("Expense not found or update failed.");
        }
    }

    /**
     * UI flow for deleting an expense.
     */
    private static void deleteExpense() {
        viewExpenses();
        System.out.print("Enter ID of expense to delete: ");
        String id = scanner.nextLine();

        if (expenseManager.deleteExpense(id, userManager.getCurrentUser())) {
            System.out.println("Expense deleted.");
        } else {
            System.out.println("Expense not found.");
        }
    }

    /**
     * Displays reports.
     */
    private static void viewReports() {
        List<Expense> expenses = expenseManager.getExpensesByUser(userManager.getCurrentUser());
        reportService.generateCategoryReport(expenses);
    }
}
