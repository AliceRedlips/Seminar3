package controller;

import integration.*;
import model.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för Controller-klassen.
 */
public class ControllerTest {
    private Controller controller;

    
    @BeforeEach
    public void setUp() {
        Register register = new Register(1000.0);
        ExternalInventorySystem inventory = new ExternalInventorySystem();
        ExternalAccountingSystem accounting = new ExternalAccountingSystem();
        DiscountDatabase discountDB = new DiscountDatabase();
        controller = new Controller(register, inventory, accounting, discountDB);
        controller.startNewSale();
    }

    /**
     * Testar att ett korrekt artikel-ID kan scannas och läggas till försäljningen.
     */
    @Test
    public void testScanItemValid() throws ItemNotFoundException, DatabaseFailureException {
        controller.scanItem("001");
        assertNotNull(controller.getSaleDTO());
        assertEquals(1, controller.getSaleDTO().getSoldItems().size());
    }

    /**
     * Testar att ItemNotFoundException kastas vid ogiltigt artikel-ID.
     */
    @Test
    public void testScanItem_ItemNotFoundExceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            controller.scanItem("999");
        });
    }

    /**
     * Testar att DatabaseFailureException kastas när databasfel simuleras.
     */
    @Test
    public void testScanItem_DatabaseFailureExceptionThrown() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.scanItem("fail");
        });
    }
}
