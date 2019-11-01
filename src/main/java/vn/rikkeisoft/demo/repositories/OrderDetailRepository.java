package vn.rikkeisoft.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.rikkeisoft.demo.entity.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAll();
}
