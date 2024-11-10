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
}
