package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.domain.product.ProductInformation;
import store.util.ProductInputValidator;
import store.util.StockInputValidator;

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
                List<ProductInformation> products = productInputValidator.validateProductInput(input);
                stockInputValidator.validateProductDetails(products);
                return products;
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

}
