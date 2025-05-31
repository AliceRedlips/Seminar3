package integration;

import model.ItemDTO;

/**
 * Representerar det externa lagersystemet som tillhandahåller artikelinformation.
 */
public class ExternalInventorySystem {

    /**
     * Hämtar artikelinformation baserat på artikel-ID.
     *
     * @param itemID Artikelns ID.
     * @return ItemDTO-objekt med artikelinformation.
     * @throws ItemNotFoundException Om artikeln inte finns i systemet.
     * @throws DatabaseFailureException Om databasen inte kan nås.
     */
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
