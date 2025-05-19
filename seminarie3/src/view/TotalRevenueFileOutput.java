package view;

import model.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Loggar total försäljning till en fil när ny betalning sker.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private double totalRevenue = 0;

    @Override
    public void newPayment(double amount) {
        totalRevenue += amount;
        logToFile(totalRevenue);
    }

    private void logToFile(double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("total_revenue_log.txt", true))) {
            writer.printf("Total försäljning hittills: %.2f kr%n", total);

        } catch (IOException e) {
            System.out.println("Kunde inte skriva till loggfil: " + e.getMessage());
        }
    }
}
