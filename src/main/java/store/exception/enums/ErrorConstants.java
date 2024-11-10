package store.exception.enums;

public enum ErrorConstants {
    ERROR_MESSAGE_PREFIX("[ERROR]"),
    INPUT_AGAIN_MESSAGE("다시 입력해 주세요."),
    SPACE(" ");

    private final String value;

    ErrorConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}