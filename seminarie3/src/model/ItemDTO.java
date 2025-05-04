package model;

public class ItemDTO {
    private String itemId;
    private String name;
    private double price;
    private double vat;

    public ItemDTO(String itemId, String name, double price, double vat) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    public String getItemId() {
        return itemId;
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
}
