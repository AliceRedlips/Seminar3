package integration;

/**
 * Thrown when the inventory system cannot be reached, simulating a database error.
 */
public class DatabaseFailureException extends Exception {
    public DatabaseFailureException(String message) {
        super(message);
    }
}
