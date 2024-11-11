package store.domain.receipt;

import java.util.List;
import store.domain.order.OrderItem;

public class Receipt {

    private final List<OrderItem> orderedItems;
    private final int eventDiscount;
    private final int membershipDiscount;
    private final int totalAmount;

    public Receipt(List<OrderItem> orderedItems, int eventDiscount, int membershipDiscount,
                   int totalAmount) {
        this.orderedItems = orderedItems;
        this.eventDiscount = eventDiscount;
        this.membershipDiscount = membershipDiscount;
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }

    public int getEventDiscount() {
        return eventDiscount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
