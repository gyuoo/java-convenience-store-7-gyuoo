package store.global.util;

import static store.domain.promotion.PromotionFactory.createPromotion;

import java.time.LocalDate;
import java.util.List;
import store.domain.product.Product;
import store.domain.product.ProductFactory;
import store.domain.promotion.Promotion;
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
        String promotionName = values.get(3);
        
        Promotion promotion = promotionRepository.getPromotionByName(promotionName);
        return ProductFactory.createProduct(name, price, quantity, promotion);
    }

    public Promotion parsePromotion(List<String> values) {
        String name = values.get(0);
        int buy = Integer.parseInt(values.get(1));
        int get = Integer.parseInt(values.get(2));
        LocalDate startDate = LocalDate.parse(values.get(3));
        LocalDate endDate = LocalDate.parse(values.get(4));

        return createPromotion(name, buy, get, startDate, endDate);
    }
}
