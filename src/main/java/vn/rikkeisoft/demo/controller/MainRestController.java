package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.rikkeisoft.demo.repositories.AccountRepository;
import vn.rikkeisoft.demo.service.dto.AccountDTO;

@RestController
@RequestMapping("/api")
public class MainRestController {
    @Autowired
    AccountRepository accountRepository;

    @DeleteMapping("/account/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody AccountDTO dto) {
        accountRepository.deleteById(dto.getId());
        return ResponseEntity.ok().build();
    }
}
