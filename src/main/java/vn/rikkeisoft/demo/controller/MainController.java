package vn.rikkeisoft.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("pageTitle","Home");
        return "index";
    }
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("pageTitle","Login");
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
}
