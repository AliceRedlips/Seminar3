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

        saleIsValid &= scanAndHandle("001", 2);  
        saleIsValid &= scanAndHandle("002", 1);  

        if (saleIsValid) {
            SaleDTO saleInfo = controller.getSaleDTO();
            displayItems(saleInfo.getSoldItems());
            System.out.printf("Totalpris (före rabatt): %.2f kr%n", saleInfo.getTotalPrice());

            controller.requestDiscount("vip123");

            SaleDTO discountedSale = controller.getSaleDTO();
            System.out.printf("Totalpris (efter rabatt): %.2f kr%n", discountedSale.getTotalPrice());

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
     * @param quantity Antalet att lägga till.
     * @return true om artikeln skannades korrekt, annars false.
     */
    private boolean scanAndHandle(String itemId, int quantity) {
        try {
            controller.scanItem(itemId, quantity);
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

    private void displayItems(List<SoldItem> soldItems) {
        for (SoldItem sold : soldItems) {
            double totalItemPrice = (sold.getItem().getPrice() + sold.getItem().getPrice() * sold.getItem().getVAT()) * sold.getQuantity();
            System.out.printf("%s %d x %.2f = %.2f kr%n",
                    sold.getName(), sold.getQuantity(),
                    sold.getItem().getPrice(), totalItemPrice);
        }
    }

    private void displayReceipt(Receipt receipt) {
        System.out.println("\n--- Kvitto ---");
        System.out.println("Tidpunkt: " + receipt.getTimeOfSale());
        System.out.println();
        System.out.println("Artiklar:");
        for (SoldItem sold : receipt.getItems()) {
            double itemTotal = (sold.getPrice() + sold.getPrice() * sold.getVAT()) * sold.getQuantity();
            System.out.printf(" %s x%d à %.2f SEK = %.2f SEK (moms: %.1f%%)%n",
                    sold.getName(), sold.getQuantity(), sold.getPrice(),
                    itemTotal, sold.getVAT() * 100);
        }
        System.out.println();
        System.out.printf("Total: %.2f SEK%n", receipt.getTotalPrice());
        System.out.printf("Betalat: %.2f SEK%n", receipt.getAmountPaid());
        System.out.printf("Växel: %.2f SEK%n", receipt.getChange());
        System.out.println("--- Slut på kvitto ---");
    }
}
