package view;

import controller.Controller;
import integration.DatabaseFailureException;
import integration.ItemNotFoundException;
import model.*;

import java.util.List;

/**
 * Representerar användargränssnittet och hanterar interaktionen med användaren.
 */
public class View {
    private Controller controller;
    private final ErrorLogger logger = new ErrorLogger();

    /**
     * Skapar en ny View och registrerar observers för intäktsrapportering.
     *
     * @param controller Huvudkontrollern för applikationen.
     */
    public View(Controller controller) {
        this.controller = controller;
        controller.addRevenueObserver(new TotalRevenueView());
        controller.addRevenueObserver(new TotalRevenueFileOutput());
    }

    /**
     * Simulerar en försäljning med artikel-ID och undantagshantering.
     */
    public void runFakeSale() {
        controller.startNewSale();

        boolean saleIsValid = true;

        saleIsValid &= scanAndHandle("001");
        saleIsValid &= scanAndHandle("002");
        // För att testa felhantering kan du avkommentera dessa:
        // saleIsValid &= scanAndHandle("abc"); // ogiltigt ID
        // saleIsValid &= scanAndHandle("999"); // simulerat databashaveri

        if (saleIsValid) {
            SaleDTO saleInfo = controller.getSaleDTO();
            displayItems(saleInfo.getItems());
            System.out.println("Totalpris (före rabatt): " + saleInfo.getTotalPrice() + " kr");

            controller.requestDiscount("vip123");

            SaleDTO discountedSale = controller.getSaleDTO();
            System.out.println("Totalpris (efter rabatt): " + discountedSale.getTotalPrice() + " kr");

            Receipt receipt = controller.pay(100);
            displayReceipt(receipt);
        } else {
            System.out.println("Försäljningen avbröts. Ingen kvitto genereras.");
        }
    }

    /**
     * Försöker skanna en artikel och hanterar eventuella undantag.
     *
     * @param itemId Artikelns ID.
     * @return true om artikeln skannades korrekt, annars false.
     */
    private boolean scanAndHandle(String itemId) {
        try {
            controller.scanItem(itemId);
            return true;
        } catch (ItemNotFoundException e) {
            System.out.println(" Fel: Artikel med ID '" + itemId + "' hittades inte.");
            logger.logException(e);
            return false;
        } catch (DatabaseFailureException e) {
            System.out.println(" Fel: Kunde inte ansluta till databasen.");
            logger.logException(e);
            return false;
        }
    }

    private void displayItems(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            System.out.println("Artikel: " + item.getName() +
                    " - Pris: " + item.getPrice() + " kr (moms: " + (item.getVAT() * 100) + "%)");
        }
    }

    private void displayReceipt(Receipt receipt) {
        System.out.println("\n--- Kvitto ---");
        displayItems(receipt.getItems());
        System.out.println("Total: " + receipt.getTotalPrice() + " kr");
        System.out.println("Betalat: " + receipt.getAmountPaid() + " kr");
        System.out.println("Växel: " + receipt.getChange() + " kr");
        System.out.println("Tid: " + receipt.getTimeOfSale());
    }
}
