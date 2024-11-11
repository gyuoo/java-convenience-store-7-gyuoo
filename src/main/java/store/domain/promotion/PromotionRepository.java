package store.domain.promotion;

import java.util.HashMap;
import java.util.Map;

public class PromotionRepository {

    private final Map<String, Promotion> promotionCache = new HashMap<>();

    public void save(Promotion promotion) {
        promotionCache.put(promotion.getName(), promotion);
    }

    public Promotion getPromotionByName(String name) {
        return promotionCache.get(name);
    }

    public boolean exists(String name) {
        return promotionCache.containsKey(name);
    }
}
