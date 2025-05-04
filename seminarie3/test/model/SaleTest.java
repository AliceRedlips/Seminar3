package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void testAddItemAndTotalPrice() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("001", "Mjölk", 10.0, 0.12);
        sale.addItem(item1);
        double expected = 10.0 + (10.0 * 0.12);
        assertEquals(expected, sale.getTotalPrice(), 0.001);
    }

    @Test
    public void testApplyDiscount() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("002", "Bröd", 20.0, 0.12);
        sale.addItem(item);
        sale.applyDiscount(0.25);
        double base = 20.0 + (20.0 * 0.12);
        double expected = base * 0.75;
        assertEquals(expected, sale.getTotalPrice(), 0.001);
    }
}