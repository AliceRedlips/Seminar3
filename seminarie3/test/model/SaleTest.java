package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för Sale-klassen.
 */
public class SaleTest {

    /**
     * Testar att en artikel läggs till och totalpriset beräknas korrekt.
     */
    @Test
    public void testAddItemAndTotalPrice() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("001", "Mjölk", 10.0, 0.12);
        sale.addItem(item1);
        double expected = 10.0 + (10.0 * 0.12);
        assertEquals(expected, sale.getTotalPrice(), 0.001);
    }

    /**
     * Testar att rabatten appliceras korrekt.
     */
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

    /**
     * Testar att totalpriset beräknas korrekt med flera artiklar.
     */
    @Test
    public void testTotalPriceWithMultipleItems() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("001", "Mjölk", 10.0, 0.12);
        ItemDTO item2 = new ItemDTO("002", "Bröd", 20.0, 0.12);
        sale.addItem(item1);
        sale.addItem(item2);
        double expected = (10.0 * 1.12) + (20.0 * 1.12);
        assertEquals(expected, sale.getTotalPrice(), 0.001);
    }

    /**
     * Testar att rabatt appliceras korrekt på flera artiklar.
     */
    @Test
    public void testApplyDiscountWithMultipleItems() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("001", "Mjölk", 10.0, 0.12);
        ItemDTO item2 = new ItemDTO("002", "Bröd", 20.0, 0.12);
        sale.addItem(item1);
        sale.addItem(item2);
        sale.applyDiscount(0.15);
        double expected = ((10.0 * 1.12) + (20.0 * 1.12)) * (1 - 0.15);
        assertEquals(expected, sale.getTotalPrice(), 0.001);
    }

    /**
     * Testar att discountApplied-flaggan uppdateras korrekt.
     */
    @Test
    public void testDiscountAppliedFlag() {
        Sale sale = new Sale();
        assertFalse(sale.isDiscountApplied(), "Rabatten ska initialt inte vara applicerad.");
        sale.applyDiscount(0.10);
        assertTrue(sale.isDiscountApplied(), "Rabatten ska vara applicerad efter anrop.");
    }
}
