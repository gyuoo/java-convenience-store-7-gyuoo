package store.exception.enums;

public enum ErrorMessage {
    INVALID_FORMAT("올바르지 않은 형식으로 입력했습니다."),
    PRODUCT_NOT_FOUND("존재하지 않는 상품입니다."),
    EMPTY_INPUT("입력은 비어 있을 수 없습니다."),
    INVALID_QUANTITY_FORMAT("수량은 숫자 형식이어야 합니다."),
    INVALID_QUANTITY_AMOUNT("수량은 1 이상이어야 합니다."),
    EXCEED_STOCK_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다."),
    INVALID_COMMON_INPUT("잘못된 입력입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ErrorConstants.ERROR_MESSAGE_PREFIX.getValue() + ErrorConstants.SPACE.getValue() + message
                + ErrorConstants.INPUT_AGAIN_MESSAGE.getValue();
    }
}
