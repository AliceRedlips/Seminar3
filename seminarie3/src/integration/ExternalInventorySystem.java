package integration;

import model.ItemDTO;

public class ExternalInventorySystem {

    public ItemDTO getItemDescription(String itemId) {
        // Simulerad databas med en hårdkodad artikel
        if (itemId.equals("001")) {
            return new ItemDTO("001", "Mjölk", 10.0, 0.12);
        } else if (itemId.equals("002")) {
            return new ItemDTO("002", "Bröd", 20.0, 0.12);
        } else {
            return null; // Artikel hittades inte
        }
    }
}
