package store.domain.product;

import store.domain.promotion.Promotion;

public class ProductFactory {

    public static Product createProduct(String name, int price, int quantity, Promotion promotion) {
        if (promotion == null) {
            return new Product(name, price, quantity);
        }
        return new PromotionProduct(name, price, quantity, promotion);
    }
}
