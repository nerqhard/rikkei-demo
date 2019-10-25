package vn.rikkeisoft.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
