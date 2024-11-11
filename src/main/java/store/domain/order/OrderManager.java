package store.domain.order;

import java.util.List;
import store.domain.product.ProductInformation;
import store.domain.product.ProductRepository;
import store.view.InputView;
import store.view.OutputView;

public class OrderManager {

    private final ProductRepository productRepository;
    private final OutputView outputView;
    private final InputView inputView;

    public OrderManager(ProductRepository productRepository, OutputView outputView,
        InputView inputView) {
        this.productRepository = productRepository;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public Order createOrder(List<ProductInformation> userProducts) {
        List<OrderItem> items = userProducts.stream()
            .map(info -> new OrderItem(productRepository.findProduct(info.getName()),
                info.getQuantity()))
            .toList();
        return new Order(items, productRepository, outputView, inputView);
    }
}
