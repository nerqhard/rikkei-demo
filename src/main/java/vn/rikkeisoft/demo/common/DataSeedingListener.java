package vn.rikkeisoft.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.rikkeisoft.demo.model.entity.Account;
import vn.rikkeisoft.demo.model.entity.Role;
import vn.rikkeisoft.demo.repositories.AccountRepository;
import vn.rikkeisoft.demo.repositories.RoleRepository;

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
        if (accountRepository.findByUsername("admin") == null) {
            Account admin = new Account();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setFullName("Nguyen Quang Ha");
            admin.setActive(true);
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRole("ROLE_ADMIN"));
            roles.add(roleRepository.findByRole("ROLE_MEMBER"));
            admin.setRoles(roles);
            accountRepository.save(admin);
        }

        //Create Member
        if (accountRepository.findByUsername("member1") == null) {
            Account user = new Account();
            user.setUsername("member1");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setFullName("Nguyen Van A");
            user.setActive(true);
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRole("ROLE_MEMBER"));
            user.setRoles(roles);
            accountRepository.save(user);
        }
    }
}