package vn.rikkeisoft.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.rikkeisoft.demo.entity.AccountEntity;
import vn.rikkeisoft.demo.entity.Role;
import vn.rikkeisoft.demo.repositories.AccountRepository;
import vn.rikkeisoft.demo.repositories.RoleRepository;

import java.sql.Timestamp;
import java.util.HashSet;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByRole("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByRole("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

        //Create Admin
        if (accountRepository.findByUsername("administrator") == null) {
            AccountEntity admin = new AccountEntity();
            admin.setUsername("administrator");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setFullName("Administrator");
            admin.setEmail("quangha.fresher@gmail.com");
            admin.setCreateDate(new Timestamp(System.currentTimeMillis()));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRole("ROLE_ADMIN"));
            roles.add(roleRepository.findByRole("ROLE_MEMBER"));
            admin.setRoles(roles);
            accountRepository.save(admin);
        }
    }
}