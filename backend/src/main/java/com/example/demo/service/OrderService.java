package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductPropertiesRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final ProductPropertiesRepository productPropertiesRepository;
    private final OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository, ProductPropertiesRepository productPropertiesRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.productPropertiesRepository = productPropertiesRepository;
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    public void updateOrder(Order order) {
        if (order.getId() != null) {
            Order existingOrder = getOrderById((long) order.getId());
            if (existingOrder != null) {
                existingOrder.setProducts(order.getProducts());
                existingOrder.setOrderTime(order.getOrderTime());
                existingOrder.setOrderStatus(order.getOrderStatus());
                existingOrder.setTotalCost(order.getTotalCost());
                existingOrder.setStartTime(order.getStartTime());
                existingOrder.setFinishTime(order.getFinishTime());
                orderRepository.save(existingOrder);
            }
        }
    }

    public Order getActiveOrder(Long userId) {

        Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus(userId, OrderStatus.ACTIVE);
        return activeOrder;
    }

    public void updateOrder(Long userId, OrderStatus status) {

        Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus(userId, OrderStatus.ACTIVE);

        if (activeOrder != null) {
            activeOrder.setOrderStatus(status);
            orderRepository.save(activeOrder);
        }
    }


    public void cancelActiveOrder(Long userId) {
        Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus(userId, OrderStatus.ACTIVE);

        if (activeOrder != null) {
            activeOrder.setOrderStatus(OrderStatus.CANCELED_BY_USER);
            orderRepository.save(activeOrder);
        }

    }

    public void approveOrder(Long userId) {
        Order waiting = orderRepository.findOrderByUserIdAndOrderStatus(userId, OrderStatus.WAITING_FOR_RECEIVING);
        if (waiting != null) {
            waiting.setOrderStatus(OrderStatus.ACTIVE);
            orderRepository.save(waiting);
        }

    }

    public void finishOrder(Long userId) {
        Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus(userId, OrderStatus.ACTIVE);

        if (activeOrder != null) {
            activeOrder.setOrderStatus(OrderStatus.FINISHED);
            orderRepository.save(activeOrder);
        }
    }

    public List<Order> getAllOrders(Long user_id) {
        List<Order> orderList = orderRepository.findAllByUserId(user_id);
        return orderList;
    }
}
