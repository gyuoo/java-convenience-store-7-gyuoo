package store.view;

import store.domain.product.Product;
import store.domain.product.PromotionProduct;

public class OutputFormatter {

    public String formatProductMessage(Product product) {
        if (product instanceof PromotionProduct promotionProduct
            && promotionProduct.isPromotionApplicable()) {
            return formatPromotionProductMessage(promotionProduct);
        }
        if (product.isOutOfStock()) {
            return formatOutOfStockMessage(product);
        }
        return formatAvailableProductMessage(product);
    }

    private String formatPromotionProductMessage(PromotionProduct product) {
        return String.format(
            ConsoleMessage.PRODUCT_AVAILABLE_WITH_PROMOTION.getMessage(),
            product.getName(),
            formatPrice(product.getPrice()),
            product.getQuantity(),
            product.getPromotion().getName()
        );
    }

    private String formatOutOfStockMessage(Product product) {
        return String.format(
            ConsoleMessage.PRODUCT_OUT_OF_STOCK.getMessage(),
            product.getName(),
            formatPrice(product.getPrice())
        );
    }

    private String formatAvailableProductMessage(Product product) {
        return String.format(
            ConsoleMessage.PRODUCT_AVAILABLE.getMessage(),
            product.getName(),
            formatPrice(product.getPrice()),
            product.getQuantity()
        );
    }

    private String formatPrice(int price) {
        return String.format("%,d", price);
    }
}
