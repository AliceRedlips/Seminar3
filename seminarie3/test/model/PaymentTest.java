package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void testCalculateChange() {
        Payment payment = new Payment(100);
        payment.calculateChange(80);
        assertEquals(20, payment.getChange(), 0.001);
    }
}