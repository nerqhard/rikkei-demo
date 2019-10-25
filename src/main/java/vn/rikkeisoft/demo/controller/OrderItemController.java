package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.rikkeisoft.demo.entity.Order;
import vn.rikkeisoft.demo.repositories.OrderItemRepository;
import vn.rikkeisoft.demo.repositories.OrderRepository;

@Controller
@RequestMapping("/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{id}")
    public String get(Model model, @PathVariable("id") long id) {
        Order order = orderRepository.findById(id).get();
        model.addAttribute("orders", orderItemRepository.findAllByOrder(order));
        model.addAttribute("idOther", id);
        return "orderItem";
    }
}
