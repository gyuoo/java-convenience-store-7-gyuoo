package store.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import store.domain.product.Product;
import store.domain.product.PromotionProduct;

public class OutputFormatter {

    public String formatProductListMessage(List<Product> products) {
        StringBuilder result = new StringBuilder();

        Map<String, List<Product>> groupedProducts = groupProductsByName(products);
        groupedProducts.forEach((name, productGroup) -> {
            result.append(formatProductGroup(productGroup)).append(System.lineSeparator());
        });

        return result.toString();
    }

    private Map<String, List<Product>> groupProductsByName(List<Product> products) {
        return products.stream()
            .collect(Collectors.groupingBy(Product::getName));
    }

    private String formatProductGroup(List<Product> productGroup) {
        StringBuilder result = new StringBuilder();
        boolean hasNonPromotionProduct = containsNonPromotionProduct(productGroup);
        boolean hasPromotionProduct = containsPromotionProduct(productGroup);
        productGroup.forEach(
            product -> result.append(formatProductMessage(product)).append(System.lineSeparator()));
        if (hasPromotionProduct && !hasNonPromotionProduct) {
            result.append(formatOutOfStockMessageForPromotion(productGroup))
                .append(System.lineSeparator());
        }
        return result.toString();
    }

    private boolean containsNonPromotionProduct(List<Product> productGroup) {
        return productGroup.stream().anyMatch(product -> !(product instanceof PromotionProduct));
    }

    private boolean containsPromotionProduct(List<Product> productGroup) {
        return productGroup.stream().anyMatch(product -> product instanceof PromotionProduct);
    }

    private String formatOutOfStockMessageForPromotion(List<Product> productGroup) {
        Product promotionProduct = productGroup.stream()
            .filter(product -> product instanceof PromotionProduct)
            .findFirst()
            .orElse(null);

        if (promotionProduct != null) {
            return formatOutOfStockMessage(promotionProduct);
        }
        return "";
    }

    public String formatProductMessage(Product product) {
        if (product instanceof PromotionProduct promotionProduct) {
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
