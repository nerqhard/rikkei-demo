package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.rikkeisoft.demo.repositories.OrderItemRepository;

@Controller
@RequestMapping("/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("orderItemList", orderItemRepository.findAll());
        return "orderItem";
    }
}
