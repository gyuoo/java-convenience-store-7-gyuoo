package store.domain.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<String, Product> productCache = new HashMap<>();

    public ProductRepository(List<Product> products) {
        for (Product product : products) {
            save(product); // 초기화 시 기존 제품 저장
        }
    }

    public void save(Product product) {
        productCache.put(product.getName(), product);
    }

    public Product getProductByName(String name) {
        return productCache.get(name);
    }

    public boolean exists(String name) {
        return productCache.containsKey(name);
    }
}
