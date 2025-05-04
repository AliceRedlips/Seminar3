package model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private List<ItemDTO> items;
    private double discountRate;
    private boolean discountApplied;

    public Sale() {
        this.items = new ArrayList<>();
        this.discountRate = 0;
        this.discountApplied = false;
    }

    public void addItem(ItemDTO item) {
        items.add(item);
    }

    public List<ItemDTO> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        double total = 0;
        for (ItemDTO item : items) {
            total += item.getPrice() + item.getPrice() * item.getVAT();
        }
        if (discountApplied) {
            total = total * (1 - discountRate);
        }
        return total;
    }

    public void applyDiscount(double discountRate) {
        this.discountRate = discountRate;
        this.discountApplied = true;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }

    public SaleDTO createSaleDTO() {
        return new SaleDTO(getItems(), getTotalPrice(), discountApplied);
    }
    
}
