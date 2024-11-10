package store.view;

import static store.view.ConsoleMessage.PRODUCT_AVAILABLE;
import static store.view.ConsoleMessage.PRODUCT_AVAILABLE_WITH_PROMOTION;
import static store.view.ConsoleMessage.PRODUCT_OUT_OF_STOCK;

import store.domain.product.Product;
import store.domain.product.PromotionProduct;

public class StockMessageChecker {

    public String getStockMessage(Product product) {
        if (product.isOutOfStock()) {
            return String.format(PRODUCT_OUT_OF_STOCK.getMessage(), product.getName(), product.getPrice());
        }
        if (product instanceof PromotionProduct promotionProduct && promotionProduct.isPromotionApplicable()) {
            return String.format(PRODUCT_AVAILABLE_WITH_PROMOTION.getMessage(), promotionProduct.getName(),
                    promotionProduct.getPrice(),
                    promotionProduct.getQuantity(), promotionProduct.getPromotion().getName());
        }
        return String.format(PRODUCT_AVAILABLE.getMessage(), product.getName(), product.getPrice(),
                product.getQuantity());
    }
}
