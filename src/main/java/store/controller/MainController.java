package store.controller;

import java.io.IOException;
import java.util.List;
import store.domain.product.Product;
import store.domain.product.ProductInformation;
import store.domain.promotion.Promotion;
import store.service.FileService;
import store.view.InputView;
import store.view.OutputView;

public class MainController {

    private final FileService fileService;
    private final InputView inputView;
    private final OutputView outputView;
    private final OrderController orderController;

    public MainController(FileService fileService, InputView inputView, OutputView outputView,
        OrderController orderController) {
        this.fileService = fileService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderController = orderController;
    }

    public void run() throws IOException {
        outputView.printWelcomeMessage();

        List<Promotion> promotions = fileService.getPromotions();
        List<Product> products = fileService.getProducts();

        outputView.printProducts();
        List<ProductInformation> userProducts = inputView.askForProductsToPurchase();
        orderController.processOrder(userProducts);

    }
}
