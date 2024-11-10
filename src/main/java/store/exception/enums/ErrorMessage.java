package store.exception.enums;

public enum ErrorMessage {
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ErrorConstants.ERROR_MESSAGE_PREFIX.getValue() + ErrorConstants.SPACE.getValue() + message
                + ErrorConstants.INPUT_AGAIN_MESSAGE.getValue();
    }
}
