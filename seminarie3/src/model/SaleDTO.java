package model;

import java.util.List;

/**
 * Data Transfer Object (DTO) för att transportera information om en försäljning.
 */
public class SaleDTO {
    private List<ItemDTO> items;
    private double totalPrice;
    private boolean discountApplied;

    /**
     * Skapar en SaleDTO med information om försäljningen.
     *
     * @param items Artiklar i försäljningen.
     * @param totalPrice Totalpriset för försäljningen.
     * @param discountApplied Om rabatt har tillämpats.
     */
    public SaleDTO(List<ItemDTO> items, double totalPrice, boolean discountApplied) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }
}
