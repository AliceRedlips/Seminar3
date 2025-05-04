package view;

import controller.Controller;
import model.Receipt;
import model.SaleDTO;
import model.ItemDTO;

import java.util.List;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void runFakeSale() {
        controller.startNewSale();

        controller.scanItem("001");
        controller.scanItem("002");

        SaleDTO saleInfo = controller.getSaleDTO();
        displayItems(saleInfo.getItems());
        System.out.println("Totalpris (före rabatt): " + saleInfo.getTotalPrice() + " kr");

        controller.requestDiscount("vip123");

        SaleDTO discountedSale = controller.getSaleDTO();
        System.out.println("Totalpris (efter rabatt): " + discountedSale.getTotalPrice() + " kr");

        Receipt receipt = controller.pay(100);
        displayReceipt(receipt);
    }

    private void displayItems(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            System.out.println("Artikel: " + item.getName() +
                    " - Pris: " + item.getPrice() + " kr (moms: " + (item.getVAT() * 100) + "%)");
        }
    }

    private void displayReceipt(Receipt receipt) {
        System.out.println("\n--- Kvitto ---");
        displayItems(receipt.getItems());
        System.out.println("Total: " + receipt.getTotalPrice() + " kr");
        System.out.println("Betalat: " + receipt.getAmountPaid() + " kr");
        System.out.println("Växel: " + receipt.getChange() + " kr");
        System.out.println("Tid: " + receipt.getTimeOfSale());
    }
}
