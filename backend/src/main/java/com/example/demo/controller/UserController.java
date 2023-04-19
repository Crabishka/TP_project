package com.example.demo.controller;

import com.example.demo.EntityDTO.ProductSizeDTO;
import com.example.demo.EntityDTO.UserAuthDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.ProductProperty;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;

    private final UserRepository userRepository;

    public UserController(UserService userService, OrderService orderService, ProductService productService, UserRepository userRepository) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return order;
    }

    @GetMapping("/products")
    public List<ProductProperty> getAllProducts() throws Exception {
        return productService.getAllProductsProperty();
    }

    @GetMapping("/products/{id}")
    public ProductProperty getProductById(@PathVariable Long id) {
        ProductProperty product = productService.getProductById(id);
        return product;
    }

    @PostMapping("/products/add/{user_id}/{product_id}")
    public void addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam(name = "size") int size) {
        userService.addProductToCart(userId, productId, size);
    }

    @GetMapping("/products/size")
    public ProductSizeDTO getProductsSizeByDate(@RequestParam(name = "date") ZonedDateTime date, @RequestParam(name = "productProperty") ProductProperty productProperty) {
        return productService.getProductSizes(date, productProperty);
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

    @PutMapping("/orders/cancel/{user_id}")
    public void cancelActiveOrder(@PathVariable Long userId) {
        orderService.cancelActiveOrder(userId);
    }

    @PostMapping("/users/login")
    public void authorizeUser(@RequestBody UserAuthDTO userAuthDTO) throws AuthenticationException {
        userService.authorizeUser(userAuthDTO);
    }


}

