
package model;

import java.util.List;

/**
 * Data Transfer Object (DTO) för att transportera information om en försäljning.
 */
public class SaleDTO {
    private List<SoldItemDTO> soldItems;
    private double totalPrice;
    private boolean discountApplied;

    /**
     * Skapar en SaleDTO med information om försäljningen.
     *
     * @param soldItems Sålda artiklar i försäljningen.
     * @param totalPrice Totalpriset för försäljningen.
     * @param discountApplied Om rabatt har tillämpats.
     */
    public SaleDTO(List<SoldItemDTO> soldItems, double totalPrice, boolean discountApplied) {
        this.soldItems = soldItems;
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
    }

    public List<SoldItemDTO> getSoldItems() {
        return soldItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }
}
