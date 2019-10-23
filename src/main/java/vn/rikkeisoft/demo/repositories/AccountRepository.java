package vn.rikkeisoft.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.rikkeisoft.demo.model.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}