package integration;

import model.Receipt;

/**
 * Representerar det externa bokföringssystemet som loggar försäljningar.
 */
public class ExternalAccountingSystem {

    /**
     * Loggar försäljningen till det externa bokföringssystemet.
     *
     * @param receipt Kvitto som innehåller försäljningsdata.
     */
    public void logSale(Receipt receipt) {
        System.out.println("Försäljning loggad till bokföringssystem:");
        System.out.println("Total: " + receipt.getTotalPrice() + " kr");
        System.out.println("Tidpunkt: " + receipt.getTimeOfSale());
    }
}
