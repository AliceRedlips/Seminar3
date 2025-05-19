package view;

import controller.Controller;
import integration.DatabaseFailureException;
import integration.ItemNotFoundException;
import model.Receipt;
import model.SaleDTO;
import model.ItemDTO;

import java.util.List;

/**
 * Represents the view in the application. Responsible for simulating a user interface and interacting with the controller.
 */
public class View {
    private final Controller controller;
    private final ErrorLogger logger = new ErrorLogger();

    /**
     * Creates a new View object and registers observers for revenue reporting.
     *
     * @param controller The controller that manages the application logic.
     */
    public View(Controller controller) {
        this.controller = controller;
    
        // Registrera observers
        controller.addRevenueObserver(new TotalRevenueView());
        controller.addRevenueObserver(new TotalRevenueFileOutput());
    }
    


    /**
     * Simulates a sale session with hardcoded items and a payment.
     * Handles exceptions by notifying users and logging technical details.
     */
    public void runFakeSale() {
        // Registrera observers
        controller.addRevenueObserver(new TotalRevenueView());
    
        // Börja ny försäljning
        controller.startNewSale();
    
        // Lägg till artiklar
        scanAndHandle("001");
        scanAndHandle("002");
    
        // Visa priser före och efter rabatt
        SaleDTO saleInfo = controller.getSaleDTO();
        displayItems(saleInfo.getItems());
        System.out.println("Totalpris (före rabatt): " + saleInfo.getTotalPrice() + " kr");
    
        controller.requestDiscount("vip123");
    
        SaleDTO discountedSale = controller.getSaleDTO();
        System.out.println("Totalpris (efter rabatt): " + discountedSale.getTotalPrice() + " kr");
    
        // Betalning (trigger observer)
        Receipt receipt = controller.pay(100);
        displayReceipt(receipt);
    }
    

    /**
     * Attempts to scan an item and handles any thrown exceptions.
     *
     * @param itemId The ID of the item to scan.
     */
    private void scanAndHandle(String itemID) {
        try {
            controller.scanItem(itemID);
        } catch (ItemNotFoundException e) {
            System.out.println("Fel: Artikel-ID \"" + itemID + "\" kunde inte hittas.");
            logger.logException(e);
        } catch (DatabaseFailureException e) {
            System.out.println("Systemfel: Databasen kunde inte nås för ID \"" + itemID + "\".");
            logger.logException(e);
        } catch (Exception e) {
            System.out.println("Oväntat fel vid scanning av ID \"" + itemID + "\": " + e.getMessage());
            logger.logException(e);
        }
    }

    /**
     * Displays a list of scanned items on the console.
     *
     * @param items The list of ItemDTOs representing items in the sale.
     */
    private void displayItems(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            System.out.println("Artikel: " + item.getName() +
                    " - Pris: " + item.getPrice() + " kr (moms: " + (item.getVAT() * 100) + "%)");
        }
    }


    /**
     * Displays a list of scanned items on the console.
     *
     * @param items The list of ItemDTOs representing items in the sale.
     */
    private void displayReceipt(Receipt receipt) {
        System.out.println("\n--- Kvitto ---");
        displayItems(receipt.getItems());
        System.out.println("Total: " + receipt.getTotalPrice() + " kr");
        System.out.println("Betalat: " + receipt.getAmountPaid() + " kr");
        System.out.println("Växel: " + receipt.getChange() + " kr");
        System.out.println("Tid: " + receipt.getTimeOfSale());
    }
}
