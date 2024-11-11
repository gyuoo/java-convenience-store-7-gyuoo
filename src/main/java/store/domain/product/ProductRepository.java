package store.domain.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // 불변성을 위해 새 리스트 반환
    }

    public boolean exists(String productName) {
        return products.stream().anyMatch(product -> product.getName().equals(productName));
    }

    public boolean isStockSufficient(String productName, int requiredQuantity) {
        int promotionStock = getPromotionStock(productName);
        return promotionStock >= requiredQuantity ||
            (promotionStock > 0
                && getRegularStock(productName) + promotionStock >= requiredQuantity) ||
            (promotionStock == 0 && getRegularStock(productName) >= requiredQuantity);
    }

    public void reduceStock(String productName, int requiredQuantity) {
        int promotionStock = getPromotionStock(productName);
        if (promotionStock >= requiredQuantity) {
            reducePromotionStock(productName, requiredQuantity);
        }
        if (promotionStock < requiredQuantity) {
            reducePromotionStock(productName, promotionStock);
            reduceRegularStock(productName, requiredQuantity - promotionStock);
        }
    }

    public int getPromotionStock(String productName) {
        return products.stream()
            .filter(product -> product instanceof PromotionProduct && product.getName()
                .equals(productName))
            .mapToInt(Product::getQuantity)
            .sum();
    }

    private int getRegularStock(String productName) {
        return products.stream()
            .filter(product -> !(product instanceof PromotionProduct) && product.getName()
                .equals(productName))
            .mapToInt(Product::getQuantity)
            .sum();
    }

    private void reducePromotionStock(String productName, int quantity) {
        products.stream()
            .filter(product -> product instanceof PromotionProduct && product.getName()
                .equals(productName))
            .findFirst()
            .ifPresent(product -> product.reduceQuantity(quantity));
    }

    private void reduceRegularStock(String productName, int quantity) {
        products.stream()
            .filter(product -> !(product instanceof PromotionProduct) && product.getName()
                .equals(productName))
            .findFirst()
            .ifPresent(product -> product.reduceQuantity(quantity));
    }
}
