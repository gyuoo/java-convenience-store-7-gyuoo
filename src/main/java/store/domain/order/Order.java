package store.domain.order;

import java.util.List;
import store.domain.discount.DiscountCalculator;
import store.domain.product.ProductRepository;
import store.view.ConsoleMessage;
import store.view.InputView;
import store.view.OutputView;

public class Order {

    private final List<OrderItem> orderItems;
    private final ProductRepository productRepository;
    private final OutputView outputView;
    private final InputView inputView;
    private final DiscountCalculator discountCalculator;

    public Order(List<OrderItem> orderItems, ProductRepository productRepository,
                 OutputView outputView, InputView inputView, DiscountCalculator discountCalculator) {
        this.orderItems = orderItems;
        this.productRepository = productRepository;
        this.outputView = outputView;
        this.inputView = inputView;
        this.discountCalculator = discountCalculator;
    }

    public void processItems() {
        for (OrderItem item : orderItems) {
            if (isStockAvailable(item)) {
                processItem(item);
            }
        }
    }

    private boolean isStockAvailable(OrderItem item) {
        if (!productRepository.isStockSufficient(item.getProductName(), item.getQuantity())) {
            outputView.printMessage(ConsoleMessage.PRODUCT_OUT_OF_STOCK.getMessage());
            return false;
        }
        return true;
    }

    private void processItem(OrderItem item) {
        int promoStock = productRepository.getPromotionStock(item.getProductName());
        if (isPartialPromotionAvailable(item, promoStock) && !handlePartialPromotion(item,
                promoStock)) {
            return;
        }
        productRepository.reduceStock(item.getProductName(), item.getQuantity());
    }

    private boolean isPartialPromotionAvailable(OrderItem item, int promoStock) {
        return promoStock < item.getQuantity() && promoStock > 0;
    }

    private boolean handlePartialPromotion(OrderItem item, int promotionStock) {
        int nonPromotionQuantity = item.getQuantity() - promotionStock;
        String message = String.format(
                ConsoleMessage.PROMOTION_STOCK_INSUFFICIENT.getMessage(),
                item.getProductName(), nonPromotionQuantity
        );
        return inputView.askForPurchaseWithoutPromotion();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

//    public Receipt getReceipt() {
//        int totalAmount = discountCalculator.calculateTotalPrice();
//        int promotionDiscountAmount = calculateDiscountedQuantity();
//        int membershipDiscountAmount = membershipDiscount.applyMembershipDiscount();
//        return new Receipt(orderItems, promotionDiscountAmount, membershipDiscountAmount, totalAmount);
//    }

    private int calculateTotalAmount() {
        return orderItems.stream()
                .mapToInt(
                        item -> item.getQuantity() * productRepository.findProduct(item.getProductName())
                                .getPrice())
                .sum();
    }
}
