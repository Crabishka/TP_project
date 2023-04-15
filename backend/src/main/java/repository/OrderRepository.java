package repository;

import entity.Order;
import entity.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByOrderTime(ZonedDateTime date);

    Order findOrderByUserIdAndOrderStatus_Active(Long userId);

    Order findOrderByUserIdAndOrderStatus_WaitingForReceiving(Long userId);
}
