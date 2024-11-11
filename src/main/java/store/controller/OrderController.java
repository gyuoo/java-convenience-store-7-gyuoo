package store.controller;

import java.util.List;
import store.domain.order.Order;
import store.domain.order.OrderManager;
import store.domain.product.ProductInformation;

public class OrderController {

    private final OrderManager orderManager;
    
    public OrderController(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public void processOrder(List<ProductInformation> userProducts) {
        Order order = orderManager.createOrder(userProducts);
        order.processItems();
    }
}
