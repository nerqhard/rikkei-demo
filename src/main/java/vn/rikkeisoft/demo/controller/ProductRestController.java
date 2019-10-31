package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.rikkeisoft.demo.repositories.ProductRepository;
import vn.rikkeisoft.demo.service.ProductService;
import vn.rikkeisoft.demo.service.dto.ProductDTO;
import vn.rikkeisoft.demo.service.mapper.ProductMapper;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductService productService;

    @DeleteMapping("/products/delete")
    public void deleteProduct(@RequestBody ProductDTO dto) {
        productRepository.deleteById(dto.getId());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updatePr(@PathVariable(name = "id") Long id) {
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDTO> updateAccount(@RequestBody ProductDTO productDTO) {
        ProductDTO dto = productService.findById(productDTO.getId());
        dto.setCode(productDTO.getCode());
        dto.setName(productDTO.getName());
        dto.setPrice(productDTO.getPrice());
        dto.setImage(productDTO.getImage());

        dto = productService.save(dto);

        return ResponseEntity.ok(dto);
    }
    @GetMapping("/products/buy/{id}")
    public ResponseEntity<ProductDTO> buyProduct(@PathVariable(name = "id") Long id) {
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok(dto);
    }
//    @PostMapping("/products/buy/")
//    public ResponseEntity<Ot> accessNumBuy() {
//        ProductDTO dto = productService.findById();
//        return ResponseEntity.ok(dto);
//    }
}
