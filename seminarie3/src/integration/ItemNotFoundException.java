package integration;

/**
 * Thrown when an item with a specific ID could not be found in the inventory.
 */
public class ItemNotFoundException extends Exception {
    private final String itemID;

    public ItemNotFoundException(String itemID) {
        super("Item with ID '" + itemID + "' was not found.");
        this.itemID = itemID;
    }

    public String getItemID() {
        return itemID;
    }
}
