package store.util;

import static store.exception.enums.ErrorMessage.EMPTY_INPUT;
import static store.exception.enums.ErrorMessage.INVALID_FORMAT;

import java.util.Arrays;
import java.util.List;

public class InputValidator {

    private static final String PRODUCT_SEPARATOR = ",";
    private static final String NAME_QUANTITY_SEPARATOR = "-";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String SPACE = " ";

    public String validateProductInput(String input) {
        if (isNullOrEmpty(input)) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
        List<String> products = Arrays.stream(input.split(PRODUCT_SEPARATOR)).toList();
        for (String product : products) {
            if (!isValidProductFormat(product.trim())) {
                throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
            }
        }
        return input;
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private boolean isValidProductFormat(String product) {
        if (!isWrappedWithBrackets(product)) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
        String content = removeBrackets(product);
        List<String> parts = splitNameAndQuantity(content);

        validateProductName(parts.get(0));
        validateProductQuantity(parts.get(1));
        return true;
    }

    private String removeBrackets(String input) {
        return input.substring(1, input.length() - 1);
    }

    private List<String> splitNameAndQuantity(String content) {
        List<String> parts = Arrays.stream(content.split(NAME_QUANTITY_SEPARATOR)).toList();
        if (parts.size() != 2) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
        return parts;
    }

    private void validateProductName(String name) {
        String trimmedName = removeSpace(name);
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
    }

    private void validateProductQuantity(String quantity) {
        String trimmedQuantity = removeSpace(quantity);
        if (!isNumeric(trimmedQuantity)) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    private boolean isWrappedWithBrackets(String str) {
        return str.startsWith(OPEN_BRACKET) && str.endsWith(CLOSE_BRACKET);
    }

    private String removeSpace(String value) {
        return value.replace(SPACE, "");
    }

    private boolean isNumeric(String input) {
        return input.chars().allMatch(Character::isDigit);
    }
}
