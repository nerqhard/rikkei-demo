package vn.rikkeisoft.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.model.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
