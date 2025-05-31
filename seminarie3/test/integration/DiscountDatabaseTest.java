package integration;

import model.Discount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för DiscountDatabase-klassen.
 */
public class DiscountDatabaseTest {

    /**
     * Testar att rätt rabatt returneras för en giltig VIP-kund.
     */
    @Test
    public void testFindDiscount() {
        DiscountDatabase db = new DiscountDatabase();
        Discount discount = db.findDiscount("vip123");
        assertEquals(0.15, discount.getRate(), 0.001);
    }
}
