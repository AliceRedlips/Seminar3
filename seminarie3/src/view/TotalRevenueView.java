package view;

import model.RevenueObserver;

/**
 * Observer som skriver ut total inkomst till terminalen.
 */
public class TotalRevenueView implements RevenueObserver {
    private double totalRevenue = 0;

    @Override
    public void newPayment(double amount) {
        totalRevenue += amount;
        System.out.printf(">> Total försäljning hittills: %.2f kr%n", totalRevenue);

    }
}
