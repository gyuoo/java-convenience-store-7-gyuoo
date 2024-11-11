package store.view;

public enum ConsoleMessage {
    // Welcome
    WELCOME_MESSAGE("안녕하세요. W편의점입니다."),

    // Product
    CURRENT_PRODUCTS("현재 보유하고 있는 상품입니다."),
    PRODUCT_AVAILABLE("- %s %s원 %s개"),
    PRODUCT_AVAILABLE_WITH_PROMOTION("- %s %s원 %s개 %s"),
    PRODUCT_OUT_OF_STOCK("- %s %s원 재고 없음"),

    // Input
    ENTER_PRODUCTS_TO_PURCHASE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    PROMOTION_AVAILABLE("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    PROMOTION_STOCK_INSUFFICIENT("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    ASK_MEMBERSHIP_DISCOUNT("멤버십 할인을 받으시겠습니까? (Y/N)"),
    ASK_ADDITIONAL_PURCHASE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),

    // Receipt
    RECEIPT_HEADER("==============W 편의점================"),
    RECEIPT_GIFT_HEADER("============증\t정==============="),
    RECEIPT_FOOTER("===================================="),
    PRODUCT_LIST_HEADER("상품명\t\t수량\t금액"),
    TOTAL_PURCHASE_AMOUNT("총구매액\t\t%d\t%,d"),
    EVENT_DISCOUNT("행사할인\t\t\t-%,d"),
    MEMBERSHIP_DISCOUNT("멤버십할인\t\t\t-%,d"),
    AMOUNT_TO_PAY("내실돈\t\t\t %,d");

    private final String message;

    ConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
