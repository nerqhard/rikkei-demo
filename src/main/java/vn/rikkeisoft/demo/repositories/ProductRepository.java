package vn.rikkeisoft.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
