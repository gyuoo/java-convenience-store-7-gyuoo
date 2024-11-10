package store.domain.discount;

import store.domain.promotion.Promotion;

public class PromotionDiscount {

    private final Promotion promotion;

    public PromotionDiscount(Promotion promotion) {
        this.promotion = promotion;
    }

    public int calculateDiscountedQuantity(int quantity) {
        int buyQuantity = promotion.getBuyQuantity();
        int getQuantity = promotion.getGetQuantity();

        if (quantity >= buyQuantity) {
            return (quantity / buyQuantity) * getQuantity;
        }
        return 0;
    }

    public boolean isEligibleForPromotion(int quantity) {
        return quantity >= promotion.getBuyQuantity();
    }
}
