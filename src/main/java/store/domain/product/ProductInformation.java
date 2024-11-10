package store.domain.product;

public class ProductInformation {
    
    private final String name;
    private final int quantity;

    public ProductInformation(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
