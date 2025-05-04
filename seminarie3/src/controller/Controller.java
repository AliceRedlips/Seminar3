package controller;

import integration.DiscountDatabase;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import model.*;

public class Controller {
    private Sale sale;
    private Register register;
    private ExternalInventorySystem inventorySystem;
    private ExternalAccountingSystem accountingSystem;
    private DiscountDatabase discountDatabase;

    public Controller(Register register, ExternalInventorySystem inventorySystem,
                      ExternalAccountingSystem accountingSystem, DiscountDatabase discountDatabase) {
        this.register = register;
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.discountDatabase = discountDatabase;
    }

    public void startNewSale() {
        sale = new Sale();
    }

    public void scanItem(String itemId) {
        ItemDTO item = inventorySystem.getItemDescription(itemId);
        if (item != null) {
            sale.addItem(item);
        } else {
            System.out.println("Artikeln kunde inte hittas.");
        }
    }

    public void requestDiscount(String customerId) {
        Discount discount = discountDatabase.findDiscount(customerId);
        sale.applyDiscount(discount.getRate());
    }

    public SaleDTO getSaleDTO() {
        return sale.createSaleDTO();
    }

    public Receipt pay(double amountPaid) {
        Payment payment = new Payment(amountPaid);
        payment.calculateChange(sale.getTotalPrice());

        register.updateBalance(payment);
        SaleDTO saleDTO = sale.createSaleDTO();
        Receipt receipt = new Receipt(saleDTO, payment);
        accountingSystem.logSale(receipt);

        return receipt;
    }
}
