package store.domain.order;

import store.domain.product.Product;
import store.domain.product.PromotionProduct;
import store.domain.promotion.Promotion;

public class OrderItem {

    private final String productName;
    private final int quantity;
    private final Promotion promotion;

    public OrderItem(Product product, int quantity) {
        Promotion promotion;
        this.productName = product.getName();
        this.quantity = quantity;
        promotion = null;
        if (product instanceof PromotionProduct promotionProduct) {
            promotion = promotionProduct.getPromotion();
        }
        this.promotion = promotion;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public boolean hasPromotion() {
        return promotion != null;
    }
}
