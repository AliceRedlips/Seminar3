package startup;

import controller.Controller;
import integration.DiscountDatabase;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import model.Register;
import view.View;

public class Main {
    public static void main(String[] args) {
        // Skapa instanser av externa system
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        DiscountDatabase discountDatabase = new DiscountDatabase();
        Register register = new Register(1000.0); // startsaldo

        // Skapa controller och view
        Controller controller = new Controller(register, inventorySystem, accountingSystem, discountDatabase);
        View view = new View(controller);

        // Kör ett testsäljflöde
        view.runFakeSale();
        view.runFakeSale();
    }
}
