package vn.rikkeisoft.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.rikkeisoft.demo.service.dto.AccountDTO;


import java.util.List;

public interface AccountService {

    AccountDTO save(AccountDTO dto);

    void delete(Long id);

    List<AccountDTO> findAll();

    AccountDTO findById(Long id);

    AccountDTO findByUsername(String name);

}
