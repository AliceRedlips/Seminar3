package model;

/**
 * Representerar kassan där betalningar lagras.
 */
public class Register {
    private double balance;

    /**
     * Skapar en ny kassa med ett startbelopp.
     *
     * @param initialBalance Kassans startsaldo.
     */
    public Register(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Uppdaterar kassans saldo med en ny betalning.
     *
     * @param payment Betalningen som ska läggas till.
     */
    public void updateBalance(Payment payment) {
        this.balance += payment.getAmountPaid();
    }

    /**
     * Returnerar kassans nuvarande saldo.
     *
     * @return Kassans saldo.
     */
    public double getBalance() {
        return balance;
    }
}
