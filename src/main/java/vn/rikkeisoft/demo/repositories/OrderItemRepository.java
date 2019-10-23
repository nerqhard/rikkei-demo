package vn.rikkeisoft.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.model.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
