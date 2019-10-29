package vn.rikkeisoft.demo.service;

import vn.rikkeisoft.demo.service.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO save(ProductDTO dto);

    void delete(Long id);

    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    ProductDTO findByCode(String code);

}
