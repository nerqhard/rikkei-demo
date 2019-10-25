package vn.rikkeisoft.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.entity.Order;
import vn.rikkeisoft.demo.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrder(Order order);
}
