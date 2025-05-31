package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för Payment-klassen.
 */
public class PaymentTest {

    /**
     * Testar att växeln beräknas korrekt.
     */
    @Test
    public void testCalculateChange() {
        Payment payment = new Payment(100);
        payment.calculateChange(80);
        assertEquals(20, payment.getChange(), 0.001);
    }
}
