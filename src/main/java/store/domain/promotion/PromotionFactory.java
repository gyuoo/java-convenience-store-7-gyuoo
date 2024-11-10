package store.domain.promotion;

import java.time.LocalDate;

public class PromotionFactory {

    public static Promotion createPromotion(String name, int buyQuantity, int getQuantity, LocalDate startDate,
                                            LocalDate endDate) {
        return new Promotion(name, buyQuantity, getQuantity, startDate, endDate);
    }

}
