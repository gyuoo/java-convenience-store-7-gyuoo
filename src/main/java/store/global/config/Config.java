package store.global.config;

import store.controller.MainController;
import store.controller.OrderController;
import store.domain.product.ProductRepository;
import store.domain.promotion.PromotionRepository;
import store.global.util.FileParser;
import store.global.util.ProductInputValidator;
import store.global.util.StockInputValidator;
import store.service.FileService;
import store.view.InputView;
import store.view.OutputFormatter;
import store.view.OutputView;
import store.view.StockMessageChecker;

public class Config {

    private final PromotionRepository promotionRepository = new PromotionRepository();
    private final ProductRepository productRepository = new ProductRepository();
    private final FileParser fileParser = new FileParser(promotionRepository);
    private final FileService fileService = new FileService(fileParser, productRepository,
        promotionRepository);
    private final ProductInputValidator productInputValidator = new ProductInputValidator();
    private final StockInputValidator stockInputValidator = new StockInputValidator(
        productRepository);
    private final StockMessageChecker stockMessageChecker = new StockMessageChecker();
    private final OutputFormatter outputFormatter = new OutputFormatter();
    private final OutputView outputView = new OutputView(outputFormatter, productRepository);
    private final InputView inputView = new InputView(outputView, stockInputValidator,
        productInputValidator);
    private final OrderController orderController = new OrderController(inputView, outputView,
        productRepository);
    private final MainController controller = new MainController(fileService, inputView, outputView,
        orderController);

    public MainController mainController() {
        return controller;
    }
}
