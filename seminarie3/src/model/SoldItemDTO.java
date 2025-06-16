package model;

/**
 * Data Transfer Object (DTO) för en såld artikel.
 * Innehåller endast nödvändig information för visning, utan affärslogik.
 */
public class SoldItemDTO {
    private final String itemID;
    private final String name;
    private final double price;
    private final double vat;
    private final int quantity;

    /**
     * Skapar ett nytt DTO-objekt för en såld artikel.
     *
     * @param itemID   Artikel-ID.
     * @param name     Artikelns namn.
     * @param price    Pris exklusive moms.
     * @param vat      Moms som decimal (t.ex. 0.12).
     * @param quantity Antal sålda enheter.
     */
    public SoldItemDTO(String itemID, String name, double price, double vat, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.quantity = quantity;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getVAT() {
        return vat;
    }

    public int getQuantity() {
        return quantity;
    }
}
