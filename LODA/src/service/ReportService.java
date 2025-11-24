package service;

import model.Expense;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates reports and analytics for expenses.
 */
public class ReportService {

    private static final Map<String, Double> BUDGET_LIMITS = new HashMap<>();

    static {
        // Hardcoded budget limits for demonstration
        BUDGET_LIMITS.put("Food", 500.0);
        BUDGET_LIMITS.put("Travel", 200.0);
        BUDGET_LIMITS.put("Utilities", 150.0);
        BUDGET_LIMITS.put("Entertainment", 100.0);
    }

    /**
     * Generates a spending report by category.
     *
     * @param expenses List of user expenses.
     */
    public void generateCategoryReport(List<Expense> expenses) {
        Map<String, Double> totals = new HashMap<>();

        for (Expense e : expenses) {
            totals.put(e.getCategory(), totals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }

        System.out.println("\n--- Spending by Category ---");
        for (Map.Entry<String, Double> entry : totals.entrySet()) {
            String category = entry.getKey();
            double total = entry.getValue();
            System.out.printf("%s: $%.2f", category, total);

            // Check budget
            if (BUDGET_LIMITS.containsKey(category)) {
                double limit = BUDGET_LIMITS.get(category);
                if (total > limit) {
                    System.out.printf(" [WARNING: Exceeded budget of $%.2f]", limit);
                } else {
                    System.out.printf(" (Budget: $%.2f)", limit);
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
