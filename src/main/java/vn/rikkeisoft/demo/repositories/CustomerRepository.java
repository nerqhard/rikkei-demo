package vn.rikkeisoft.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.model.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
