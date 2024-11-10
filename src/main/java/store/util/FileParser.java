package store.util;

import static store.domain.product.ProductFactory.createProduct;

import java.util.List;
import store.domain.product.Product;
import store.domain.promotion.PromotionRepository;

public class FileParser {

    private final PromotionRepository promotionRepository;

    public FileParser(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Product parseProduct(List<String> values) {
        String name = values.get(0);
        int price = Integer.parseInt(values.get(1));
        int quantity = Integer.parseInt(values.get(2));
        String promotion = values.get(3);

        return createProduct(name, price, quantity, promotionRepository.getPromotionByName(promotion));
    }
}
