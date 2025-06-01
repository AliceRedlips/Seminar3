package controller;

import integration.*;
import model.Register;
import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller coordinates the application's flow between the view and model layers.
 * It handles all incoming calls from the user interface.
 */
public class Controller {
    private Sale sale;
    private Register register;
    private ExternalInventorySystem inventorySystem;
    private ExternalAccountingSystem accountingSystem;
    private DiscountDatabase discountDatabase;
    private final List<RevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Creates a new instance of Controller and connects system dependencies.
     */
    public Controller(Register register, ExternalInventorySystem inventorySystem,
                      ExternalAccountingSystem accountingSystem, DiscountDatabase discountDatabase) {
        this.register = register;
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.discountDatabase = discountDatabase;
    }

    /**
     * Starts a new sale.
     */
    public void startNewSale() {
        sale = new Sale();
    }

    /**
     * Scans an item by identifier and adds it to the current sale.
     *
     * @param itemId The identifier of the item.
     * @param quantity The quantity of the item to add.
     * @throws ItemNotFoundException       If item not found in inventory.
     * @throws DatabaseFailureException    If the inventory system fails.
     */
    public void scanItem(String itemId, int quantity) throws ItemNotFoundException, DatabaseFailureException {
        ItemDTO item = inventorySystem.getItemDescription(itemId);
        sale.addItem(item, quantity);
    }

    /**
     * Applies a discount to the current sale for the given customer.
     *
     * @param customerId Identifier of the customer.
     */
    public void requestDiscount(String customerId) {
        Discount discount = discountDatabase.findDiscount(customerId);
        sale.applyDiscount(discount.getRate());
    }

    /**
     * Returns a summary of the current sale.
     *
     * @return SaleDTO containing items, total price, and discount status.
     */
    public SaleDTO getSaleDTO() {
        return sale.createSaleDTO();
    }

    /**
     * Handles the payment and generates the final receipt.
     *
     * @param amountPaid Amount paid by the customer.
     * @return The generated receipt.
     */
    public Receipt pay(double amountPaid) {
        Payment payment = new Payment(amountPaid);
        payment.calculateChange(sale.getTotalPrice());

        register.updateBalance(payment);
        SaleDTO saleDTO = sale.createSaleDTO();
        Receipt receipt = new Receipt(saleDTO, payment);
        accountingSystem.logSale(receipt);

        notifyObservers(sale.getTotalPrice());

        return receipt;
    }

    /**
     * Adds an observer that will be notified of revenue updates.
     *
     * @param observer The observer to add.
     */
    public void addRevenueObserver(RevenueObserver observer) {
        revenueObservers.add(observer);
    }

    /**
     * Notifies all registered observers of a new sale revenue.
     *
     * @param amount The sale amount.
     */
    private void notifyObservers(double amount) {
        for (RevenueObserver obs : revenueObservers) {
            obs.newPayment(amount);
        }
    }
}
