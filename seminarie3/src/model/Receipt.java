package model;

import java.time.LocalDateTime;
import java.util.List;

public class Receipt {
    private final List<SoldItemDTO> items;
    private final double totalPrice;
    private final double amountPaid;
    private final double change;
    private final LocalDateTime timeOfSale;

    public Receipt(SaleDTO saleDTO, Payment payment) {
        this.items = saleDTO.getItems();
        this.totalPrice = saleDTO.getTotalPrice();
        this.amountPaid = payment.getAmountPaid();
        this.change = payment.getChange();
        this.timeOfSale = LocalDateTime.now();
    }

    public List<SoldItemDTO> getItems() {
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
