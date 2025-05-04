package integration;

import model.Receipt;

public class ExternalAccountingSystem {

    public void logSale(Receipt receipt) {
        // Simulerad loggning, i verkligheten skulle detta skickas till ett externt system
        System.out.println("Försäljning loggad till bokföringssystem:");
        System.out.println("Total: " + receipt.getTotalPrice() + " kr");
        System.out.println("Tidpunkt: " + receipt.getTimeOfSale());
    }
}
