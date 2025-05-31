package startup;

import controller.Controller;
import integration.DiscountDatabase;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import model.Register;
import view.View;

/**
 * Applikationens startpunkt för Process Sale-systemet.
 * 
 * Denna klass instansierar alla nödvändiga objekt och startar en
 * simulerad försäljningssession via View.runFakeSale().
 */
public class Main {
    /**
     * Programstart.
     *
     * @param args Kommandoradsargument (används ej).
     */
    public static void main(String[] args) {
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        DiscountDatabase discountDatabase = new DiscountDatabase();
        Register register = new Register(1000.0);

        Controller controller = new Controller(register, inventorySystem, accountingSystem, discountDatabase);
        View view = new View(controller);

        view.runFakeSale();
    }
}
