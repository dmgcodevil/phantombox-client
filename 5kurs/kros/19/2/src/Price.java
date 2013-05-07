
public class Price implements Comparable<Price> {

    private String itemName;

    private String storeName;

    private double price;

    public Price() {
    }

    public Price(String itemName, String storeName, double price) {
        this.itemName = itemName;
        this.storeName = storeName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "itemName='" + itemName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Price o) {
        return storeName.compareTo(o.getStoreName());
    }
}
