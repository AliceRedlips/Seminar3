package model;

public class Register {
    private double balance;

    public Register(double initialBalance) {
        this.balance = initialBalance;
    }

    public void updateBalance(Payment payment) {
        this.balance += payment.getAmountPaid();
    }

    public double getBalance() {
        return balance;
    }
}
