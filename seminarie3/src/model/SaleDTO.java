package model;

import java.util.List;

public class SaleDTO {
    private List<ItemDTO> items;
    private double totalPrice;
    private boolean discountApplied;

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
