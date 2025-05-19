package model;

/**
 * Data Transfer Object (DTO) representing an item in the system.
 * Contains basic information about an item such as ID, name, price, and VAT.
 */
public class ItemDTO {
    private String itemId;
    private String name;
    private double price;
    private double vat;

    /**
     * Creates a new ItemDTO with specified details.
     *
     * @param itemId Unique identifier for the item.
     * @param name   The name/description of the item.
     * @param price  The price of the item (excluding VAT).
     * @param vat    The VAT rate for the item (e.g., 0.12 for 12%).
     */
    public ItemDTO(String itemId, String name, double price, double vat) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    /**
     * Returns the item ID.
     *
     * @return The unique item identifier.
     */
    public String getItemID() {
        return itemId;
    }

    /**
     * Returns the item name.
     *
     * @return The item's name or description.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the item's price (excluding VAT).
     *
     * @return The item price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the item's VAT rate.
     *
     * @return The VAT rate.
     */
    public double getVAT() {
        return vat;
    }
}
