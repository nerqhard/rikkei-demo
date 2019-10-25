package vn.rikkeisoft.demo.service;

import vn.rikkeisoft.demo.dto.AccountDTO;
import vn.rikkeisoft.demo.entity.AccountEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface AccountService {

    AccountEntity save(AccountEntity accountEntity);

    AccountEntity findByUsername(String name);

    AccountEntity findById(Long id);

    void delete(Long id);

    List<AccountEntity> findAll(Pageable pageable);

    void editRoleAccount(Long id, String role);


}
