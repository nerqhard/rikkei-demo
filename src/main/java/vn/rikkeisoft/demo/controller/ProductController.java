package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.rikkeisoft.demo.repositories.ProductRepository;
import vn.rikkeisoft.demo.service.ProductService;
import vn.rikkeisoft.demo.service.dto.ProductDTO;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/", "/**"})
    public String get(Model model, @RequestParam(value = "successMessage", required = false) String msg) {
        model.addAttribute("productList", productRepository.findAll());
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        if (msg != null && msg.equals("1")) {
            model.addAttribute("successMessage", "Add product successfully...");
        }
        return "product";
    }

    @PostMapping("/")
    public String saveProduct(ProductDTO productDTO) {
        productService.save(productDTO);
        return "redirect:/products/?successMessage=1";
    }
}
