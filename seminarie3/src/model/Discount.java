package model;

public class Discount {
    private double rate; // t.ex. 0.1 för 10 %

    public Discount(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public double applyTo(double amount) {
        return amount * (1 - rate);
    }
}
