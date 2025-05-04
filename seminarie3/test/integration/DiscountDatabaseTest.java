package integration;

import model.Discount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountDatabaseTest {

    @Test
    public void testFindDiscount() {
        DiscountDatabase db = new DiscountDatabase();
        Discount discount = db.findDiscount("vip123");
        assertEquals(0.15, discount.getRate(), 0.001);
    }
}