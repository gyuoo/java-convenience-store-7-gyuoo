package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.domain.product.ProductInformation;
import store.global.exception.enums.ErrorMessage;
import store.global.util.ProductInputValidator;
import store.global.util.StockInputValidator;

public class InputView {

    private final OutputView outputView;
    private final StockInputValidator stockInputValidator;
    private final ProductInputValidator productInputValidator;

    public InputView(OutputView outputView, StockInputValidator stockInputValidator,
        ProductInputValidator productInputValidator) {
        this.outputView = outputView;
        this.stockInputValidator = stockInputValidator;
        this.productInputValidator = productInputValidator;
    }

    public String readLine() {
        return Console.readLine();
    }

    public List<ProductInformation> askForProductsToPurchase() {
        while (true) {
            outputView.printEnterProductsToPurchase();
            String input = readLine();
            try {
                List<ProductInformation> products = productInputValidator.validateProductInput(
                    input);
                stockInputValidator.validateProductDetails(products);
                return products;
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    public boolean userConfirmed(String message) {
        while (true) {
            System.out.println(message);
            String response = readLine();
            if (response.equals("Y")) {
                return true;
            }
            if (response.equals("N")) {
                return false;
            }
            System.out.println(ErrorMessage.INVALID_COMMON_INPUT.getMessage());
        }
    }
}
