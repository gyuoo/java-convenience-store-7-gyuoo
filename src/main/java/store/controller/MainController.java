package store.controller;

import java.io.IOException;
import java.util.List;
import store.domain.order.OrderItem;
import store.domain.order.OrderManager;
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
    private final OrderManager orderManager;
    private final OrderController orderController;
    private final PaymentController paymentController;

    public MainController(FileService fileService, InputView inputView, OutputView outputView,
        OrderManager orderManager,
        OrderController orderController, PaymentController paymentController) {
        this.fileService = fileService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderManager = orderManager;
        this.orderController = orderController;
        this.paymentController = paymentController;
    }

    public void run() throws IOException {
        outputView.printWelcomeMessage();

        List<Promotion> promotions = fileService.getPromotions();
        List<Product> products = fileService.getProducts();

        outputView.printProducts();
        List<ProductInformation> userProducts = inputView.askForProductsToPurchase();
        List<OrderItem> orderedItem = orderManager.createOrderItems(userProducts);
        orderController.processOrder(userProducts);
        paymentController.processPayment(orderedItem);
    }
}
