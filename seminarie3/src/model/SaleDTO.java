
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO för att transportera information om en försäljning.
 */
public class SaleDTO {
    private final List<SoldItemDTO> soldItems;
    private final double totalPrice;
    private final boolean discountApplied;

    /**
     * Skapar en SaleDTO med information om försäljningen.
     *
     * @param soldItems Artiklar i försäljningen som SoldItemDTO.
     * @param totalPrice Totalpriset för försäljningen.
     * @param discountApplied Om rabatt har tillämpats.
     */
    public SaleDTO(List<SoldItem> soldItems, double totalPrice, boolean discountApplied) {
        this.soldItems = convertToDTOs(soldItems);
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
    }

    private List<SoldItemDTO> convertToDTOs(List<SoldItem> soldItems) {
        List<SoldItemDTO> dtos = new ArrayList<>();
        for (SoldItem sold : soldItems) {
            dtos.add(new SoldItemDTO(
                    sold.getItemID(),
                    sold.getName(),
                    sold.getPrice(),
                    sold.getVAT(),
                    sold.getQuantity()
            ));
        }
        return dtos;
    }

    public List<SoldItemDTO> getSoldItems() {
        return soldItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }
}
