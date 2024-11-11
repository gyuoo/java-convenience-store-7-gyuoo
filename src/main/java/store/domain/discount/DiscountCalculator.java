package store.domain.discount;

import store.domain.product.Product;
import store.domain.promotion.Promotion;

public class DiscountCalculator {

    private final PromotionDiscount promotionDiscount;
    private final MembershipDiscount membershipDiscount;

    public DiscountCalculator(Promotion promotion) {
        this.promotionDiscount = new PromotionDiscount(promotion);
        this.membershipDiscount = new MembershipDiscount();
    }

    public int calculateTotalPrice(Product product, int quantity, boolean applyMembershipDiscount) {
        int price = product.getPrice();
        int discountedQuantity = promotionDiscount.calculateDiscountedQuantity(quantity);
        int payableQuantity = quantity - discountedQuantity;
        int totalPrice = price * payableQuantity;

        if (applyMembershipDiscount) {
            totalPrice -= membershipDiscount.applyMembershipDiscount(totalPrice);
        }

        return totalPrice;
    }
}
