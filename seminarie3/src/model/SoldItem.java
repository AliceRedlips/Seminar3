package model;

/**
 * Representerar en såld artikel med kvantitet.
 */
public class SoldItem {
    private ItemDTO item;
    private int quantity;

    /**
     * Skapar en såld artikel.
     *
     * @param item     Artikeldatan (id, namn, pris, moms)
     * @param quantity Antal sålda enheter
     */
    public SoldItem(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Hämtar artikeldatan.
     *
     * @return ItemDTO
     */
    public ItemDTO getItem() {
        return item;
    }

    /**
     * Hämtar antalet sålda artiklar.
     *
     * @return antal
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Ökar antalet sålda artiklar.
     *
     * @param amount antal att öka med
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Hämtar artikel-ID.
     *
     * @return artikel-ID
     */
    public String getItemID() {
        return item.getItemID();
    }

    /**
     * Hämtar artikelns namn.
     *
     * @return namn
     */
    public String getName() {
        return item.getName();
    }

    /**
     * Hämtar artikelns pris exkl moms.
     *
     * @return pris
     */
    public double getPrice() {
        return item.getPrice();
    }

    /**
     * Hämtar artikelns moms.
     *
     * @return moms
     */
    public double getVAT() {
        return item.getVAT();
    }

    /**
     * Konverterar SoldItem till en SoldItemDTO.
     *
     * @return SoldItemDTO
     */
    public SoldItemDTO toDTO() {
        return new SoldItemDTO(
            item.getItemID(),
            item.getName(),
            item.getPrice(),
            item.getVAT(),
            quantity
        );
    }
}
