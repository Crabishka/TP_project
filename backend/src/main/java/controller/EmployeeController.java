package controller;

import entity.Order;
import entity.OrderStatus;
import org.springframework.web.bind.annotation.*;
import service.OrderService;
import service.ProductService;
import service.UserService;

@RestController
public class EmployeeController {

    private UserService userService;
    private OrderService orderService;

    private ProductService productService;

    @GetMapping("/employee/orders/{order_id}")
    public Order getOrderById(@PathVariable Long OrderId) {
        Order order = orderService.getOrderById(OrderId);
        return order;
    }

    @GetMapping("/employee/orders")
    public Order getAllProducts() throws Exception {
        return productService.getAllProducts();
    }

    @PutMapping("/employee/orders/approve/{user_id}")
    public void approveOrder(@PathVariable Long userId) {
        orderService.approveOrder(userId);
    }

    @PutMapping("employee/orders/finish/{user_id} ")
    public void finishOrder(@PathVariable Long userId) {
        orderService.finishOrder(userId);
    }


}
