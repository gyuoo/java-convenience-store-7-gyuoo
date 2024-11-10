package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.util.InputValidator;

public class InputView {

    private final OutputView outputView;
    private final InputValidator inputValidator;

    public InputView(OutputView outputView, InputValidator inputValidator) {
        this.outputView = outputView;
        this.inputValidator = inputValidator;
    }

    public String readLine() {
        return Console.readLine();
    }

    public String askForProductsToPurchase() {
        while (true) {
            outputView.printEnterProductsToPurchase();
            String input = readLine();
            try {
                return inputValidator.validateProductInput(input);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

}
