package vn.rikkeisoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.rikkeisoft.demo.common.EncrytedPasswordUtils;
import vn.rikkeisoft.demo.entity.AccountEntity;
import vn.rikkeisoft.demo.entity.Role;
import vn.rikkeisoft.demo.repositories.AccountRepository;
import vn.rikkeisoft.demo.repositories.RoleRepository;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class AccoutServiceImp implements AccountService  {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountEntity save(AccountEntity accountEntity) {
        accountEntity.setPassword(EncrytedPasswordUtils.encrytePassword(accountEntity.getPassword()));
        accountEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Role role = roleRepository.findByRole("MEMBER");
        accountEntity.setRoles(new HashSet<>(Arrays.asList(role)));
        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity findByUsername(String name) {
        return accountRepository.findByUsername(name);
    }

    @Override
    public AccountEntity findById(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountEntity> findAll(Pageable pageable) {
        return accountRepository.findAll();
    }

    @Override
    public void editRoleAccount(Long id, String role) {

    }
}
