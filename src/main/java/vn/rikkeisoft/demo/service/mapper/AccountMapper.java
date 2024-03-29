package vn.rikkeisoft.demo.service.mapper;

import org.springframework.stereotype.Component;
import vn.rikkeisoft.demo.entity.AccountEntity;
import vn.rikkeisoft.demo.service.dto.AccountDTO;

import java.sql.Timestamp;

@Component
public class AccountMapper implements EntityMapper<AccountDTO, AccountEntity> {

    @Override
    public AccountEntity toEntity(AccountDTO dto) {
        AccountEntity entity = new AccountEntity();
        entity.setUsername(dto.getUsername());
        entity.setFullName(dto.getFullName());
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    @Override
    public AccountDTO toDto(AccountEntity entity) {
        AccountDTO dto = new AccountDTO();
        dto.setUsername(entity.getUsername());
        dto.setFullName(entity.getFullName());
        dto.setPassword(entity.getPassword());
        dto.setRoles(entity.getRoles());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}
