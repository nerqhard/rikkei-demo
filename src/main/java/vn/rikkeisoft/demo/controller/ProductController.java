package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.rikkeisoft.demo.model.entity.Product;
import vn.rikkeisoft.demo.repositories.ProductRepository;

import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String get(Model model){
        model.addAttribute("productList",productRepository.findAll());
        return "product";
    }
}
