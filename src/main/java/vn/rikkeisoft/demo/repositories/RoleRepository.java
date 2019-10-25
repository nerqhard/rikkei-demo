package vn.rikkeisoft.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.rikkeisoft.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
