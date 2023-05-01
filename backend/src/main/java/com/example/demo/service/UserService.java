package com.example.demo.service;

import com.example.demo.EntityDTO.UserAuthDTO;
import com.example.demo.EntityDTO.JwtResponse;
import com.example.demo.autorization.JwtTokenProvider;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, ProductRepository productRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtProvider = jwtProvider;
    }


    public Optional<User> getByLogin(@NotNull String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void addProductToCart(Long userId, Long productPropertyId, double size) {
        Optional<User> user = userRepository.findById(userId);
        System.out.println("Пользователь с ID " + userId + " не найден");

        Order order = getCartingOrder(user.get());
        order.getProducts().add(productRepository.getProductBySizeAndProductPropertyId(size, productPropertyId));


    }

    public Order getCartingOrder(User user) {
        //если у юзера есть заказ в статусе картинг, то мы его возвращаем, иначе создаем
        for (Order order : user.getOrders()) {
            if (order.getOrderStatus().equals(OrderStatus.CARTING)) {
                return order;
            }
        }
        List<Product> products = new ArrayList<>();
        Order order = Order.builder().orderStatus(OrderStatus.CARTING).products(products).build();
        user.getOrders().add(order);
        return order;
    }

    public JwtResponse authorizeUser(UserAuthDTO userAuthDTO) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthDTO.getUsername(), userAuthDTO.getPassword()));
        final User user = getByLogin(userAuthDTO.getUsername()).get();
        return new JwtResponse(jwtTokenProvider.generateAccessToken(user), jwtTokenProvider.generateRefreshToken(user));
    }


}
