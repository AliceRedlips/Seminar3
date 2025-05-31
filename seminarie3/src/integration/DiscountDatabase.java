package integration;

import model.Discount;

/**
 * Representerar en rabattdatabas som kan hämta rabatter
 * baserat på kundens ID.
 */
public class DiscountDatabase {

    /**
     * Hämtar eventuell rabatt för en given kund.
     *
     * @param customerId Kundens ID.
     * @return Discount-objekt som innehåller rabattprocenten.
     */
    public Discount findDiscount(String customerId) {
        if (customerId.equals("vip123")) {
            return new Discount(0.15);
        } else {
            return new Discount(0.0);
        }
    }
}
