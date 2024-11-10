package store.util;

import java.util.List;
import store.domain.product.ProductInformation;
import store.domain.product.ProductRepository;
import store.exception.enums.ErrorMessage;

public class StockInputValidator {

    private final ProductRepository productRepository;

    public StockInputValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validateProductDetails(List<ProductInformation> products) {
        for (ProductInformation product : products) {
            String productName = product.getName();
            int quantity = product.getQuantity();

            validateProductExistence(productName);
            validateStockQuantity(productName, quantity);
        }
    }

    private void validateProductExistence(String productName) {
        if (!productRepository.exists(productName)) {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_FOUND.getMessage());
        }
    }

    private void validateStockQuantity(String productName, int quantity) {
        if (!productRepository.isStockSufficient(productName, quantity)) {
            throw new IllegalArgumentException(ErrorMessage.EXCEED_STOCK_QUANTITY.getMessage());
        }
    }
}
