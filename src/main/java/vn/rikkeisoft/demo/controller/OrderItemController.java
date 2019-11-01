package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.rikkeisoft.demo.repositories.OrderDetailRepository;

@Controller
@RequestMapping("/mycart")
public class OrderItemController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("orders", orderDetailRepository.findAll());
        return "mycart";
    }
}
