package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.rikkeisoft.demo.repositories.AccountRepository;

@Controller
@RequestMapping("/accounts")
public class AcountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("accountList", accountRepository.findAll());
        model.addAttribute("pageTitle", "Account");
        return "account";
    }
}
