package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för Register-klassen.
 */
public class RegisterTest {

    /**
     * Testar att kassans saldo uppdateras korrekt efter en betalning.
     */
    @Test
    public void testUpdateBalance() {
        Register register = new Register(500.0);
        Payment payment = new Payment(100.0);
        register.updateBalance(payment);
        assertEquals(600.0, register.getBalance(), 0.001);
    }
}
