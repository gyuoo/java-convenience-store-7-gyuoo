package store.view;

import static store.view.ConsoleMessage.CURRENT_PRODUCTS;
import static store.view.ConsoleMessage.WELCOME_MESSAGE;

import java.util.Map;
import store.domain.product.Product;

public class OutputView {

    private StockMessageChecker stockMessageChecker;

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printProducts(Map<String, Product> products) {
        StringBuilder productsInformation = new StringBuilder();
        productsInformation.append(CURRENT_PRODUCTS).append(System.lineSeparator())
                .append(System.lineSeparator());
        for (Product product : products.values()) {
            productsInformation.append(stockMessageChecker.getStockMessage(product))
                    .append(System.lineSeparator());
        }
        System.out.println(productsInformation);
    }

    public void printEnterProductsToPurchase() {
        System.out.println(ENTER_PRODUCTS_TO_PURCHASE.getMessage());
    }

    public void printPromotionAvailable(String productName) {
        System.out.println(String.format(PROMOTION_AVAILABLE.getMessage(), productName));
    }

    public void printPromotionStockInsufficient(String productName, int quantity) {
        System.out.println(String.format(PROMOTION_STOCK_INSUFFICIENT.getMessage(), productName, quantity));
    }

    public void printAskMembershipDiscount() {
        System.out.println(ASK_MEMBERSHIP_DISCOUNT.getMessage());
    }

    public void printAskAdditionalPurchase() {
        System.out.println(ASK_ADDITIONAL_PURCHASE.getMessage());
    }
}
