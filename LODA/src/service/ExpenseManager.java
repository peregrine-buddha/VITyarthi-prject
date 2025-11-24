package service;

import model.Expense;
import model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Manages expense operations (CRUD) and persistence.
 */
public class ExpenseManager {
    private static final String EXPENSE_FILE = "data/expenses.csv";
    private List<Expense> allExpenses;

    public ExpenseManager() {
        allExpenses = new ArrayList<>();
        loadExpenses();
    }

    /**
     * Adds a new expense for a user.
     *
     * @param user        The user adding the expense.
     * @param date        Date of expense.
     * @param category    Category.
     * @param amount      Amount.
     * @param description Description.
     */
    public void addExpense(User user, LocalDate date, String category, double amount, String description) {
        String id = UUID.randomUUID().toString();
        Expense expense = new Expense(id, user.getId(), date, category, amount, description);
        allExpenses.add(expense);
        saveExpenses();
    }

    /**
     * Retrieves all expenses for a specific user.
     *
     * @param user The user.
     * @return List of expenses belonging to the user.
     */
    public List<Expense> getExpensesByUser(User user) {
        return allExpenses.stream()
                .filter(e -> e.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Edits an existing expense.
     *
     * @param expenseId   The ID of the expense to edit.
     * @param user        The owner of the expense.
     * @param date        New date.
     * @param category    New category.
     * @param amount      New amount.
     * @param description New description.
     * @return true if updated, false if not found or unauthorized.
     */
    public boolean editExpense(String expenseId, User user, LocalDate date, String category, double amount, String description) {
        for (Expense e : allExpenses) {
            if (e.getId().equals(expenseId) && e.getUserId().equals(user.getId())) {
                e.setDate(date);
                e.setCategory(category);
                e.setAmount(amount);
                e.setDescription(description);
                saveExpenses();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes an expense.
     *
     * @param expenseId The ID of the expense to delete.
     * @param user      The owner of the expense.
     * @return true if deleted, false if not found or unauthorized.
     */
    public boolean deleteExpense(String expenseId, User user) {
        Iterator<Expense> iterator = allExpenses.iterator();
        while (iterator.hasNext()) {
            Expense e = iterator.next();
            if (e.getId().equals(expenseId) && e.getUserId().equals(user.getId())) {
                iterator.remove();
                saveExpenses();
                return true;
            }
        }
        return false;
    }

    /**
     * Loads expenses from the CSV file.
     */
    private void loadExpenses() {
        File file = new File(EXPENSE_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Expense expense = new Expense(
                            parts[0], // id
                            parts[1], // userId
                            LocalDate.parse(parts[2]), // date
                            parts[3], // category
                            Double.parseDouble(parts[4]), // amount
                            parts[5]  // description
                    );
                    allExpenses.add(expense);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading expenses: " + e.getMessage());
        }
    }

    /**
     * Overwrites the CSV file with the current list of expenses.
     */
    private void saveExpenses() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EXPENSE_FILE))) {
            for (Expense e : allExpenses) {
                bw.write(String.format("%s,%s,%s,%s,%.2f,%s",
                        e.getId(),
                        e.getUserId(),
                        e.getDate().toString(),
                        e.getCategory(),
                        e.getAmount(),
                        e.getDescription()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving expenses: " + e.getMessage());
        }
    }
}
