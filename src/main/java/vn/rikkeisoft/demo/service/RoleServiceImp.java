package vn.rikkeisoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.rikkeisoft.demo.entity.Role;
import vn.rikkeisoft.demo.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
