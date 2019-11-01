package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.rikkeisoft.demo.service.AccountService;
import vn.rikkeisoft.demo.service.dto.AccountDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
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
    public String admin(Model model) {
        model.addAttribute("pageTitle", "Admin");
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied(Model model) {
        model.addAttribute("pageTitle", "Error");
        return "403";
    }

    @GetMapping("/register")
    public String goToRegister(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("accountDTO", accountDTO);
        return "register";
    }

    @PostMapping("/register")
    public String progressRegiter(@Valid AccountDTO accountDTO, BindingResult bindingResult, Model model) {

        //Use Jpa find username on database
        AccountDTO username = accountService.findByUsername(accountDTO.getUsername());
        //Compare entered Username With Username data
        if (username != null) {
            bindingResult.rejectValue("username", "error.username", "Username is already used, please enter another Username");
        }
        //if the error sends a message validates ->pageRegistration
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            //save User
            accountService.save(accountDTO);
            //Add message Succsess --> Attribute
            model.addAttribute("successMessage", "Registered successfully, please login... ");
            //come to pagaLogin
            return "login";
        }
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody AccountDTO dto) {
        accountService.delete(dto.getId());
    }
}
