package service;

import entity.Order;
import entity.OrderStatus;
import repository.OrderRepository;
import repository.ProductPropertiesRepository;
import repository.ProductRepository;

import java.util.Optional;

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
                Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus_Active(userId);
                return activeOrder;
    }

    public void updateOrder(Long userId, OrderStatus status) {
                Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus_Active(userId);
                if (activeOrder != null) {
                    activeOrder.setOrderStatus(status);
                    orderRepository.save(activeOrder);
                }
            }


    public void cancelActiveOrder(Long userId) {
        Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus_Active(userId);
        if (activeOrder != null) {
            activeOrder.setOrderStatus(OrderStatus.CANCELED_BY_USER);
            orderRepository.save(activeOrder);
        }

    }

    public void approveOrder(Long userId) {
        Order vaiting = orderRepository.findOrderByUserIdAndOrderStatus_WaitingForReceiving(userId);
        if (vaiting != null) {
            vaiting.setOrderStatus(OrderStatus.ACTIVE);
            orderRepository.save(vaiting);
        }

    }

    public void finishOrder(Long userId) {
        Order activeOrder = orderRepository.findOrderByUserIdAndOrderStatus_Active(userId);
        if (activeOrder != null) {
            activeOrder.setOrderStatus(OrderStatus.FINISHED);
            orderRepository.save(activeOrder);
        }
    }
}
