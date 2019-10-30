package vn.rikkeisoft.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.rikkeisoft.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code);

}
