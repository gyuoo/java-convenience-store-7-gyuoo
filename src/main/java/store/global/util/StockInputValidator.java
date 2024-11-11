package store.global.util;

import java.util.List;
import store.domain.product.ProductInformation;
import store.domain.product.ProductRepository;

public class StockInputValidator {

    private final ProductRepository productRepository;

    public StockInputValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validateProductDetails(List<ProductInformation> products) {
        for (ProductInformation product : products) {
            String productName = product.getName();
            int quantity = product.getQuantity();

        }
    }
}
