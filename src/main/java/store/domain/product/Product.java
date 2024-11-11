package store.domain.product;

import static store.exception.enums.ErrorMessage.EXCEED_STOCK_QUANTITY;

public class Product {

    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int quantity) {
        if (quantity > this.quantity) {
            throw new IllegalArgumentException(EXCEED_STOCK_QUANTITY.getMessage());
        }
        this.quantity -= quantity;
    }

    public boolean isOutOfStock() {
        return quantity == 0;
    }
}
