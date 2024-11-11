package store.domain.order;

import java.util.List;
import store.domain.product.ProductRepository;
import store.view.ConsoleMessage;
import store.view.InputView;
import store.view.OutputView;

public class Order {

    private final List<OrderItem> orderItems;
    private final ProductRepository productRepository;
    private final OutputView outputView;
    private final InputView inputView;

    public Order(List<OrderItem> orderItems, ProductRepository productRepository,
        OutputView outputView,
        InputView inputView) {
        this.orderItems = orderItems;
        this.productRepository = productRepository;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void processItems() {
        for (OrderItem item : orderItems) {
            if (!productRepository.isStockSufficient(item.getProductName(), item.getQuantity())) {
                outputView.printMessage(ConsoleMessage.PRODUCT_OUT_OF_STOCK.getMessage());
                continue;
            }
            if (isPartialPromotionAvailable(item) && !confirmPurchaseWithoutFullPromotion(item)) {
                continue;
            }
            productRepository.reduceStock(item.getProductName(), item.getQuantity());
        }
    }

    private boolean isPartialPromotionAvailable(OrderItem item) {
        int promoStock = productRepository.getPromotionStock(item.getProductName());
        return promoStock < item.getQuantity() && promoStock > 0;
    }

    private boolean confirmPurchaseWithoutFullPromotion(OrderItem item) {
        int promoStock = productRepository.getPromotionStock(item.getProductName());
        int nonPromoQuantity = item.getQuantity() - promoStock;
        String message = String.format(
            ConsoleMessage.PROMOTION_STOCK_INSUFFICIENT.getMessage(), item.getProductName(),
            nonPromoQuantity
        );
        return inputView.userConfirmed(message);
    }
}
