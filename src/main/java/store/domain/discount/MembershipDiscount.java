package store.domain.discount;

public class MembershipDiscount {

    private static final double DISCOUNT_RATE = 0.3;
    private static final int MAX_DISCOUNT = 8000;

    public int applyMembershipDiscount(int amount) {
        int discount = (int) (amount * DISCOUNT_RATE);
        return Math.min(discount, MAX_DISCOUNT);
    }
}
