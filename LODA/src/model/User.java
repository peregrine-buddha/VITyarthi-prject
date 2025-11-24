package model;

import java.io.Serializable;

/**
 * Represents a user in the SmartSpend application.
 * Encapsulates user credentials and identification.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password; // In a real app, this should be hashed.

    /**
     * Constructor for creating a new User.
     *
     * @param id       Unique identifier for the user.
     * @param username The user's chosen username.
     * @param password The user's password.
     */
    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user's ID.
     * @return The unique ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a string representation of the user (useful for debugging).
     */
    @Override
    public String toString() {
        return "User{id='" + id + "', username='" + username + "'}";
    }
}
