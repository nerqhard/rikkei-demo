package vn.rikkeisoft.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.debugger.Page;
import vn.rikkeisoft.demo.entity.AccountEntity;

import java.awt.print.Pageable;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByUsername(String username);

    boolean existsByUsername(String username);
}