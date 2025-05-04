package model;

public class Payment {
    private double amountPaid;
    private double change;

    public Payment(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    public void calculateChange(double totalPrice) {
        this.change = amountPaid - totalPrice;
    }
}
