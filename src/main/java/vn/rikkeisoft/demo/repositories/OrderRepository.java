package vn.rikkeisoft.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
