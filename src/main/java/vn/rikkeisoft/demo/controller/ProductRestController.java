package vn.rikkeisoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.rikkeisoft.demo.repositories.ProductRepository;
import vn.rikkeisoft.demo.service.dto.ProductDTO;
import vn.rikkeisoft.demo.service.mapper.ProductMapper;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;

    @DeleteMapping("/products/delete")
    public ResponseEntity<Void> deleteProduct(@RequestBody ProductDTO dto) {
        productRepository.deleteById(dto.getId());
        return ResponseEntity.ok().build();
    }
}
