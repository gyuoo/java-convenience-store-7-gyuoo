package store.domain.order;

import java.util.List;
import java.util.stream.Collectors;
import store.domain.discount.DiscountCalculator;
import store.domain.product.ProductInformation;
import store.domain.product.ProductRepository;
import store.view.InputView;
import store.view.OutputView;

public class OrderManager {

    private final ProductRepository productRepository;
    private final OutputView outputView;
    private final InputView inputView;
    private final DiscountCalculator discountCalculator;

    public OrderManager(ProductRepository productRepository, OutputView outputView,
                        InputView inputView, DiscountCalculator discountCalculator) {
        this.productRepository = productRepository;
        this.outputView = outputView;
        this.inputView = inputView;
        this.discountCalculator = discountCalculator;
    }

    public List<OrderItem> createOrderItems(List<ProductInformation> userProducts) {
        return userProducts.stream()
                .map(info -> new OrderItem(productRepository.findProduct(info.getName()),
                        info.getQuantity()))
                .collect(Collectors.toList());
    }


    public Order createOrder(List<ProductInformation> userProducts) {
        List<OrderItem> items = userProducts.stream()
                .map(info -> new OrderItem(productRepository.findProduct(info.getName()),
                        info.getQuantity()))
                .toList();
        return new Order(items, productRepository, outputView, inputView, discountCalculator);
    }
}
