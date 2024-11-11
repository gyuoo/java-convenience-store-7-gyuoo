package store.domain.receipt;

public class ReceiptItem {

    private final String productName;
    private final int quantity;
    private final int price;

    public ReceiptItem(String productName, int quantity, int price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s\t\t%d\t%,d", productName, quantity, price * quantity);
    }
}
