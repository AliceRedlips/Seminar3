package model;

/**
 * Observer interface for receiving revenue updates.
 */
public interface RevenueObserver {
    void newPayment(double amount);
}
