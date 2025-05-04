package integration;

import model.Discount;

public class DiscountDatabase {

    public Discount findDiscount(String customerId) {
        // Simulerad rabattdatabas
        if (customerId.equals("vip123")) {
            return new Discount(0.15); // 15% rabatt
        } else {
            return new Discount(0.0); // ingen rabatt
        }
    }
}
