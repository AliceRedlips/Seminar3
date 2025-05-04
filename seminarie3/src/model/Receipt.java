package model;

import java.time.LocalDateTime;
import java.util.List;

public class Receipt {
    private List<ItemDTO> items;
    private double totalPrice;
    private double amountPaid;
    private double change;
    private LocalDateTime timeOfSale;

    public Receipt(SaleDTO saleDTO, Payment payment) {
        this.items = saleDTO.getItems();
        this.totalPrice = saleDTO.getTotalPrice();
        this.amountPaid = payment.getAmountPaid();
        this.change = payment.getChange();
        this.timeOfSale = LocalDateTime.now();
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    public LocalDateTime getTimeOfSale() {
        return timeOfSale;
    }
}
