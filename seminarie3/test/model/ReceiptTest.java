package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Tester för Receipt-klassen.
 */
public class ReceiptTest {

    /**
     * Testar att kvittot innehåller rätt antal artiklar.
     */
    @Test
    public void testReceiptContainsItems() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("001", "Mjölk", 10.0, 0.12);
        sale.addItem(item1, 2); // Lägg till 2 st av artikeln

        SaleDTO saleDTO = sale.createSaleDTO();
        Payment payment = new Payment(100);
        payment.calculateChange(sale.getTotalPrice());
        Receipt receipt = new Receipt(saleDTO, payment);

        List<SoldItem> items = receipt.getItems();
        assertEquals(1, items.size());
        assertEquals(2, items.get(0).getQuantity());
        assertTrue(items.get(0).getName().contains("Mjölk"));
    }

    /**
     * Testar att kvittots totalpris matchar försäljningen.
     */
    @Test
    public void testReceiptTotalMatchesSale() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("001", "Mjölk", 10.0, 0.12);
        ItemDTO item2 = new ItemDTO("002", "Bröd", 20.0, 0.12);
        sale.addItem(item1, 1);
        sale.addItem(item2, 1);

        SaleDTO saleDTO = sale.createSaleDTO();
        Payment payment = new Payment(100);
        payment.calculateChange(sale.getTotalPrice());
        Receipt receipt = new Receipt(saleDTO, payment);

        double expectedTotal = sale.getTotalPrice();
        assertEquals(expectedTotal, receipt.getTotalPrice(), 0.001);
    }

    /**
     * Testar att växel beräknas korrekt i kvittot.
     */
    @Test
    public void testReceiptChangeCalculation() {
        Sale sale = new Sale();
        ItemDTO item1 = new ItemDTO("001", "Mjölk", 10.0, 0.12);
        sale.addItem(item1, 2); // Lägg till 2 st av artikeln

        SaleDTO saleDTO = sale.createSaleDTO();
        Payment payment = new Payment(50);
        payment.calculateChange(sale.getTotalPrice());
        Receipt receipt = new Receipt(saleDTO, payment);

        double expectedChange = 50 - sale.getTotalPrice();
        assertEquals(expectedChange, receipt.getChange(), 0.001);
    }
}
