package service;

import model.User;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages user authentication and persistence.
 */
public class UserManager {
    private static final String USER_FILE = "data/users.csv";
    private Map<String, User> users; // In-memory cache of users
    private User currentUser;

    public UserManager() {
        users = new HashMap<>();
        loadUsers();
    }

    /**
     * Registers a new user.
     *
     * @param username The desired username.
     * @param password The desired password.
     * @return true if registration successful, false if username exists.
     */
    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        String id = UUID.randomUUID().toString();
        User newUser = new User(id, username, password);
        users.put(username, newUser);
        saveUser(newUser);
        return true;
    }

    /**
     * Authenticates a user.
     *
     * @param username The username.
     * @param password The password.
     * @return true if login successful, false otherwise.
     */
    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Gets the currently logged-in user.
     *
     * @return The current User object, or null if not logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Loads users from the CSV file into memory.
     */
    private void loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    User user = new User(parts[0], parts[1], parts[2]);
                    users.put(user.getUsername(), user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    /**
     * Appends a new user to the CSV file.
     *
     * @param user The user to save.
     */
    private void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(user.getId() + "," + user.getUsername() + "," + user.getPassword());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }
}
