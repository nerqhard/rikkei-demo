package vn.rikkeisoft.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.rikkeisoft.demo.entity.Product;
import vn.rikkeisoft.demo.service.dto.ProductDTO;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code);

}
