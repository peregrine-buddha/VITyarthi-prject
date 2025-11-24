package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a single expense record.
 */
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId; // Foreign key linking to User
    private LocalDate date;
    private String category;
    private double amount;
    private String description;

    /**
     * Constructor for creating a new Expense.
     *
     * @param id          Unique identifier for the expense.
     * @param userId      ID of the user who owns this expense.
     * @param date        Date of the expense.
     * @param category    Category (e.g., Food, Travel).
     * @param amount      Cost of the expense.
     * @param description Brief description.
     */
    public Expense(String id, String userId, LocalDate date, String category, double amount, String description) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Date: %s | Cat: %s | Amt: $%.2f | Desc: %s",
                id, date, category, amount, description);
    }
}
