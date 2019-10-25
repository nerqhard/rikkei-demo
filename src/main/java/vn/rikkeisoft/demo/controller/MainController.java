package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.rikkeisoft.demo.dto.AccountDTO;
import vn.rikkeisoft.demo.entity.AccountEntity;
import vn.rikkeisoft.demo.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("pageTitle","Home");
        return "index";
    }
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("pageTitle","Login");
        //Neu da dang nhap, co Authentication => ko the vao trang login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getCredentials() == null) {
            return "redirect:/";
        }
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("pageTitle","Admin");
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied(Model model){
        model.addAttribute("pageTitle","Error");
        return "403";
    }

    @GetMapping("/register")
    public String goToRegister(Model model){
        model.addAttribute("pageTitle","Register");

        AccountDTO accountDTO = new AccountDTO()
        model.addAttribute("accountDTO", accountDTO);
        return "register";
    }

    @PostMapping("/register")
    public String progressRegiter(@Valid AccountDTO accountDTO, BindingResult bindingResult, Model model) {

        // if @Valid accountDTO -> BindingResult has error then return registration page to display
        // message error
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        //Use Jpa find username on database
    }

}
