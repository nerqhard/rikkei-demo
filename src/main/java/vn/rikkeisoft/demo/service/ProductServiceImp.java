package vn.rikkeisoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.rikkeisoft.demo.entity.Product;
import vn.rikkeisoft.demo.repositories.ProductRepository;
import vn.rikkeisoft.demo.service.dto.ProductDTO;
import vn.rikkeisoft.demo.service.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductDTO dto) {
        Product entity = productMapper.toEntity(dto);
        productRepository.save(entity);
        return productMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> entities = productRepository.findAll();
        List<ProductDTO> dtos = new ArrayList<>();
        entities.forEach(i -> {
            dtos.add(productMapper.toDto(i));
        });
        return dtos;
    }

    @Override
    public ProductDTO findById(Long id) {
        Product entity = productRepository.findById(id).get();
        return productMapper.toDto(entity);
    }

    @Override
    public ProductDTO findByCode(String code) {

        return Optional.ofNullable(productRepository.findByCode(code)).map(productMapper::toDto).orElse(null);
    }
}
