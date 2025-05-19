package integration;

import model.ItemDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExternalInventorySystemTest {

    private final ExternalInventorySystem inventorySystem = new ExternalInventorySystem();

    @Test
    public void testFindItemSuccess() throws ItemNotFoundException, DatabaseFailureException {
        ItemDTO item = inventorySystem.getItemDescription("001");
        assertNotNull(item, "Item should not be null for valid ID.");
        assertEquals("Mjölk", item.getName(), "Incorrect item retrieved.");
    }

    @Test
    public void testFindItem_ItemNotFoundExceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            inventorySystem.getItemDescription("999"); // ogiltigt ID
        });
    }

    @Test
    public void testFindItem_DatabaseFailureExceptionThrown() {
        assertThrows(DatabaseFailureException.class, () -> {
            inventorySystem.getItemDescription("fail"); // simulerat fel
        });
    }
}
