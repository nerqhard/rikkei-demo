package vn.rikkeisoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.rikkeisoft.demo.common.EncrytedPasswordUtils;
import vn.rikkeisoft.demo.entity.AccountEntity;
import vn.rikkeisoft.demo.entity.Role;
import vn.rikkeisoft.demo.repositories.AccountRepository;
import vn.rikkeisoft.demo.repositories.RoleRepository;
import vn.rikkeisoft.demo.service.dto.AccountDTO;
import vn.rikkeisoft.demo.service.mapper.AccountMapper;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
public class AccoutServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountDTO save(AccountDTO dto) {

        AccountEntity entity = accountMapper.toEntity(dto);
        entity.setPassword(EncrytedPasswordUtils.encrytePassword(dto.getPassword()));
        Role role = roleRepository.findByRole("ROLE_MEMBER");
        entity.setRoles(new HashSet<>(Arrays.asList(role)));
        accountRepository.save(entity);

        return accountMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDTO> findAll() {
        List<AccountEntity> entities = accountRepository.findAll();
        List<AccountDTO> dtos = new ArrayList<>();
        entities.forEach(i->{
            dtos.add(accountMapper.toDto(i));
        });
        return dtos;
    }

    @Override
    public AccountDTO findById(Long id) {
        AccountEntity entity = accountRepository.findById(id).get();
        return accountMapper.toDto(entity);
    }

    @Override
    public AccountDTO findByUsername(String name) {

        return Optional.ofNullable(accountRepository.findByUsername(name)).map(accountMapper::toDto).orElse(null);
    }

}
