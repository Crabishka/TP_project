package controller;

import EntityDTO.ProductSizeDTO;
import EntityDTO.UserAuthDTO;
import EntityDTO.UserRegDTO;
import entity.Order;
import entity.OrderStatus;
import entity.Product;
import org.springframework.web.bind.annotation.*;
import service.OrderService;
import service.ProductService;
import service.UserService;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private UserService userService;
    private OrderService orderService;
    private ProductService productService;

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return order;
    }

    @GetMapping("/products")
    public Order getAllProducts() throws Exception {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product;
    }

    @PostMapping("/products/add/{user_id}/{product_id}")
    public void addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam(name = "size") int size) {
        userService.addProductToCart(userId, productId, size);
    }

    @GetMapping("/products/size")
    public ProductSizeDTO getProductsSizeByDate(@RequestParam(name = "date") String date) {
        Map<Integer, Boolean> sizeMap = productService.getProductSizes(date);
        return new ProductSizeDTO(sizeMap);
    }

    @GetMapping("/products/date")
    public List<LocalDate> getEmployedDates(@RequestParam(name = "size") int size) {
        List<LocalDate> dates = productService.getEmployedDates(size);
        return dates;
    }

    @PostMapping("/orders")
    public void updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
    }

    @GetMapping("/orders/active/{user_id}")
    public Order getActiveOrder(@PathVariable Long userId) {
        Order order = orderService.getActiveOrder(userId);
        return order;
    }

    @PutMapping("/orders/active/{user_id}")
    public void updateActiveOrderStatus(@PathVariable Long userId, @RequestBody OrderStatus status) {
        orderService.updateOrder(userId, status);
    }

    @PutMapping("/orders/active/{user_id}")
    public void cancelActiveOrder(@PathVariable Long userId) {
        orderService.cancelActiveOrder(userId);
    }

    @GetMapping("/users/login")
    public void authorizeUser(@RequestBody UserAuthDTO userAuthDTO) throws AuthenticationException {
        userService.authorizeUser(userAuthDTO);
    }


}

