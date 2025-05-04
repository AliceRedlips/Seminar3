package controller;

import integration.DiscountDatabase;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import model.Register;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void testStartNewSaleAndScanItem() {
        Controller controller = new Controller(
            new Register(1000.0),
            new ExternalInventorySystem(),
            new ExternalAccountingSystem(),
            new DiscountDatabase()
        );

        controller.startNewSale();
        controller.scanItem("001");
        assertNotNull(controller.getSaleDTO());
    }
}