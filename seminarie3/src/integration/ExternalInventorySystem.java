package integration;

import model.ItemDTO;

public class ExternalInventorySystem {

    public ItemDTO getItemDescription(String itemID) throws ItemNotFoundException, DatabaseFailureException {
        if ("fail".equals(itemID)) {
            throw new DatabaseFailureException("Database connection failed for itemID: " + itemID);
        }

        if ("001".equals(itemID)) {
            return new ItemDTO("001", "Mjölk", 10.0, 0.12);
        } else if ("002".equals(itemID)) {
            return new ItemDTO("002", "Bröd", 20.0, 0.12);
        } else {
            throw new ItemNotFoundException(itemID);
        }
    }
}
