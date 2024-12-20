package store.domain.product;

import store.domain.promotion.Promotion;
import store.global.util.DateManager;

public class PromotionProduct extends Product {

    private DateManager dateManager;
    private Promotion promotion;

    public PromotionProduct(String name, int price, int quantity, Promotion promotion) {
        super(name, price, quantity);
        this.promotion = promotion;
    }

    @Override
    public boolean isOutOfStock() {
        return super.isOutOfStock() && promotion == null;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public boolean isPromotionApplicable() {
        return promotion.isActive(dateManager.getNow());
    }
}
