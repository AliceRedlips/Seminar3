package model;

/**
 * Representerar en betalning som innehåller det betalda beloppet och växel.
 */
public class Payment {
    private double amountPaid;
    private double change;

    /**
     * Skapar en ny betalning.
     *
     * @param amountPaid Det belopp som kunden har betalat.
     */
    public Payment(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Returnerar det belopp som kunden har betalat.
     *
     * @return Betalda beloppet.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Returnerar växeln som ska ges tillbaka.
     *
     * @return Växeln.
     */
    public double getChange() {
        return change;
    }

    /**
     * Beräknar växeln som ska ges tillbaka.
     *
     * @param totalPrice Totalpriset för försäljningen.
     */
    public void calculateChange(double totalPrice) {
        this.change = amountPaid - totalPrice;
    }
}
