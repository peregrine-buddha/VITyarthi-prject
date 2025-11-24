package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for validating user input.
 */
public class InputValidator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Validates if a string is not null and not empty.
     *
     * @param input The string to check.
     * @return true if valid, false otherwise.
     */
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Validates if a double is positive.
     *
     * @param amount The amount to check.
     * @return true if positive, false otherwise.
     */
    public static boolean isPositiveAmount(double amount) {
        return amount > 0;
    }

    /**
     * Parses a date string in yyyy-MM-dd format.
     *
     * @param dateStr The date string.
     * @return The LocalDate object, or null if invalid.
     */
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
