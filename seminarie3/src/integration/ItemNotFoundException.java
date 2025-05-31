package integration;

/**
 * Thrown when an item with a specific ID could not be found in the inventory.
 */
public class ItemNotFoundException extends Exception {
    private final String itemID;

    /**
     * Skapar ett nytt undantag för en artikel som inte kunde hittas.
     *
     * @param itemID Det artikel-ID som inte kunde hittas.
     */
    public ItemNotFoundException(String itemID) {
        super("Item with ID '" + itemID + "' was not found.");
        this.itemID = itemID;
    }

    /**
     * Returnerar det artikel-ID som inte kunde hittas.
     *
     * @return Artikelns ID.
     */
    public String getItemID() {
        return itemID;
    }
}
