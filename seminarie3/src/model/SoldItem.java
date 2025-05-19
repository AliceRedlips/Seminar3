package model;

/**
 * Represents an item in a sale, including quantity and item information.
 */
public class SoldItem {
    private final ItemDTO item;
    private int quantity;

    /**
     * Creates a new SoldItem.
     *
     * @param item     The item being sold.
     * @param quantity The number of units sold.
     */
    public SoldItem(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of the sold item.
     *
     * @param amount The number of units to add.
     */
    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    /**
     * Returns the current quantity of the sold item.
     *
     * @return The quantity sold.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the original item information.
     *
     * @return The ItemDTO object representing the product.
     */
    public ItemDTO getItem() {
        return item;
    }

    /**
     * Creates a new ItemDTO with modified name and total price,
     * based on the quantity sold. Used for receipt and display purposes.
     *
     * @return An ItemDTO representing the sold item and quantity.
     */
    public ItemDTO toItemDTO() {
        double unitPriceWithVAT = item.getPrice() + item.getPrice() * item.getVAT();
        double totalPrice = unitPriceWithVAT * quantity;

        return new ItemDTO(
            item.getItemID(),
            item.getName() + " x" + quantity,
            totalPrice,
            item.getVAT()
        );
    }
}
