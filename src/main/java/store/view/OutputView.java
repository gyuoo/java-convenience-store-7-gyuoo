package store.view;

import static store.view.ConsoleMessage.ASK_ADDITIONAL_PURCHASE;
import static store.view.ConsoleMessage.ASK_MEMBERSHIP_DISCOUNT;
import static store.view.ConsoleMessage.ENTER_PRODUCTS_TO_PURCHASE;
import static store.view.ConsoleMessage.PROMOTION_AVAILABLE;
import static store.view.ConsoleMessage.PROMOTION_STOCK_INSUFFICIENT;
import static store.view.ConsoleMessage.WELCOME_MESSAGE;

import java.util.List;
import store.domain.product.Product;
import store.domain.product.ProductRepository;

public class OutputView {

    private final OutputFormatter outputFormatter;
    private final ProductRepository productRepository;

    public OutputView(OutputFormatter outputFormatter, ProductRepository productRepository) {
        this.outputFormatter = outputFormatter;
        this.productRepository = productRepository;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE.getMessage());
    }

    public void printProducts() {
        List<Product> allProducts = productRepository.getAllProducts();
        String productsInformation = outputFormatter.formatProductListMessage(allProducts);
        System.out.println(productsInformation);
    }

    public void printEnterProductsToPurchase() {
        System.out.println(ENTER_PRODUCTS_TO_PURCHASE.getMessage());
    }

    public void printPromotionAvailable(String productName) {
        System.out.println(String.format(PROMOTION_AVAILABLE.getMessage(), productName));
    }

    public void printPromotionStockInsufficient(String productName, int quantity) {
        System.out.println(
            String.format(PROMOTION_STOCK_INSUFFICIENT.getMessage(), productName, quantity));
    }

    public void printAskMembershipDiscount() {
        System.out.println(ASK_MEMBERSHIP_DISCOUNT.getMessage());
    }

    public void printAskAdditionalPurchase() {
        System.out.println(ASK_ADDITIONAL_PURCHASE.getMessage());
    }
}
