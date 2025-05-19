package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple logger that prints error information to a text file.
 */
public class ErrorLogger {
    public void logException(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("error_log.txt", true))) {
            writer.println("Exception: " + e.toString());
        } catch (IOException ioEx) {
            System.out.println("Kunde inte skriva till loggfil.");
        }
    }
}
