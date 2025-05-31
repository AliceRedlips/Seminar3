package model;

/**
 * Representerar en rabatt som kan appliceras på ett belopp.
 */
public class Discount {
    private double rate;

    /**
     * Skapar en ny rabatt.
     *
     * @param rate Rabattsatsen, t.ex. 0.1 för 10%.
     */
    public Discount(double rate) {
        this.rate = rate;
    }

    /**
     * Returnerar rabattsatsen.
     *
     * @return Rabattsatsen.
     */
    public double getRate() {
        return rate;
    }

    /**
     * Applicerar rabatten på ett belopp.
     *
     * @param amount Det belopp som rabatten ska appliceras på.
     * @return Beloppet efter att rabatten har applicerats.
     */
    public double applyTo(double amount) {
        return amount * (1 - rate);
    }
}
