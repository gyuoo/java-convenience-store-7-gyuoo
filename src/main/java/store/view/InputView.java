package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.util.ProductInputValidator;

public class InputView {

    private final OutputView outputView;
    private final ProductInputValidator productInputValidator;

    public InputView(OutputView outputView, ProductInputValidator productInputValidator) {
        this.outputView = outputView;
        this.productInputValidator = productInputValidator;
    }

    public String readLine() {
        return Console.readLine();
    }

    public String askForProductsToPurchase() {
        while (true) {
            outputView.printEnterProductsToPurchase();
            String input = readLine();
            try {
                return productInputValidator.validateProductInput(input);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

}
