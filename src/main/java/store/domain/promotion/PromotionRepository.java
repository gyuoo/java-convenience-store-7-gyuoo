package store.domain.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionRepository {
    private final Map<String, Promotion> promotionCache = new HashMap<>();

    public PromotionRepository(List<Promotion> promotions) {
        for (Promotion promotion : promotions) {
            save(promotion);  // 초기화 시 기존 프로모션 저장
        }
    }

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
