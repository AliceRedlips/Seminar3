package controller;

import integration.*;
import model.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testScanItemValid() throws ItemNotFoundException, DatabaseFailureException {
        controller.scanItem("001");
        assertNotNull(controller.getSaleDTO());
        assertEquals(1, controller.getSaleDTO().getItems().size());
    }

    @Test
    public void testScanItem_ItemNotFoundExceptionThrown() {
        assertThrows(ItemNotFoundException.class, () -> {
            controller.scanItem("999");
        });
    }

    @Test
    public void testScanItem_DatabaseFailureExceptionThrown() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.scanItem("fail");
        });
    }
}
