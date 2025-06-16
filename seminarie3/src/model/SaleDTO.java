
package model;

import java.util.List;

/**
 * DTO med försäljningsdata.
 */
public class SaleDTO {
    private List<SoldItemDTO> items;
    private double totalPrice;
    private boolean discountApplied;

    public SaleDTO(List<SoldItemDTO> items, double totalPrice, boolean discountApplied) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
    }

    public List<SoldItemDTO> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }
}
