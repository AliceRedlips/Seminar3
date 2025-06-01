package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an ongoing sale transaction, including scanned items, discounts, and total price calculation.
 */
public class Sale {
    private Map<String, SoldItem> items;
    private double discountRate;
    private boolean discountApplied;

    /**
     * Creates a new instance of a sale with no items and no discount applied.
     */
    public Sale() {
        this.items = new HashMap<>();
        this.discountRate = 0;
        this.discountApplied = false;
    }

    /**
     * Adds an item to the sale. If the item was already scanned, increases its quantity.
     *
     * @param item The item to be added or updated.
     */
    public void addItem(ItemDTO item) {
        String id = item.getItemID();
        if (items.containsKey(id)) {
            items.get(id).increaseQuantity(1);
        } else {
            items.put(id, new SoldItem(item, 1));
        }
    }

    /**
     * Returns a list of SoldItem objects representing the items in the sale.
     *
     * @return A list of all sold items including quantity.
     */
    public List<SoldItem> getSoldItems() {
        return new ArrayList<>(items.values());
    }

    /**
     * Calculates and returns the total price for the sale, including VAT and discount.
     *
     * @return The total sale price.
     */
    public double getTotalPrice() {
        double total = 0;
        for (SoldItem sold : items.values()) {
            double itemTotal = (sold.getItem().getPrice() + sold.getItem().getPrice() * sold.getItem().getVAT()) * sold.getQuantity();
            total += itemTotal;
        }
        if (discountApplied) {
            total = total * (1 - discountRate);
        }
        return total;
    }

    /**
     * Applies a discount to the sale using a given discount rate.
     *
     * @param discountRate The rate of the discount (e.g. 0.15 for 15%).
     */
    public void applyDiscount(double discountRate) {
        this.discountRate = discountRate;
        this.discountApplied = true;
    }

    /**
     * Checks whether a discount has been applied to the sale.
     *
     * @return true if a discount has been applied, otherwise false.
     */
    public boolean isDiscountApplied() {
        return discountApplied;
    }

    /**
     * Creates and returns a SaleDTO object containing current sale details.
     *
     * @return The sale data transfer object.
     */
    public SaleDTO createSaleDTO() {
        return new SaleDTO(getSoldItems(), getTotalPrice(), discountApplied);
    }
}
