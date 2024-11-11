package store.controller;

import java.util.List;
import store.domain.discount.DiscountCalculator;
import store.domain.order.OrderItem;
import store.domain.product.ProductRepository;
import store.domain.receipt.Receipt;
import store.view.InputView;
import store.view.OutputView;

public class PaymentController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ProductRepository productRepository;

    public PaymentController(InputView inputView, OutputView outputView,
        ProductRepository productRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.productRepository = productRepository;
    }

    public void processPayment(List<OrderItem> orderItems) {
        boolean isMembership = inputView.askForMembershipDiscount();
        int totalAmount = calculateTotalAmount(orderItems);
        int promotionDiscountAmount = calculatePromotionDiscount(orderItems);
        int membershipDiscountAmount = calculateMembershipDiscount(
            totalAmount - promotionDiscountAmount, isMembership);

        int finalAmount = totalAmount - promotionDiscountAmount - membershipDiscountAmount;

        Receipt receipt = new Receipt(orderItems, promotionDiscountAmount, membershipDiscountAmount,
            finalAmount);

        outputView.printReceipt(receipt);
    }

    private int calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
            .mapToInt(
                item -> item.getQuantity() * productRepository.findProduct(item.getProductName())
                    .getPrice())
            .sum();
    }

    private int calculatePromotionDiscount(List<OrderItem> orderItems) {
        int discountAmount = 0;
        for (OrderItem item : orderItems) {
            if (item.hasPromotion()) {
                DiscountCalculator discountCalculator = new DiscountCalculator(item.getPromotion());
                discountAmount += discountCalculator.calculateTotalPrice(
                    productRepository.findProduct(item.getProductName()), item.getQuantity(),
                    false);
            }
        }
        return discountAmount;
    }

    private int calculateMembershipDiscount(int amount, boolean isMembership) {
        if (isMembership) {
            DiscountCalculator discountCalculator = new DiscountCalculator(null);
            return discountCalculator.membershipDiscount.applyMembershipDiscount(amount);
        }
        return 0;
    }
}
