package integration;

import model.ItemDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för ExternalInventorySystem-klassen.
 */
public class ExternalInventorySystemTest {

    private final ExternalInventorySystem inventorySystem = new ExternalInventorySystem();

    /**
     * Testar att ett giltigt artikel-ID returnerar en artikel.
     */
    @Test
    public void testFindItemSuccess() throws ItemNotFoundException, DatabaseFailureException {
        ItemDTO item = inventorySystem.getItemDescription("001");
        assertNotNull(item, "Item should not be null for valid ID.");
        assertEquals("Mjölk", item.getName(), "Incorrect item retrieved.");
    }

    /**
     * Testar att ItemNotFoundException kastas vid ogiltigt artikel-ID.
     */
    @Test
    public void testFindItem_ItemNotFoundExceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            inventorySystem.getItemDescription("999");
        });
    }

    /**
     * Testar att DatabaseFailureException kastas vid simulerat databasfel.
     */
    @Test
    public void testFindItem_DatabaseFailureExceptionThrown() {
        assertThrows(DatabaseFailureException.class, () -> {
            inventorySystem.getItemDescription("fail");
        });
    }
}
