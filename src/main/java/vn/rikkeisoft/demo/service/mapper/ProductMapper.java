package vn.rikkeisoft.demo.service.mapper;

import org.springframework.stereotype.Component;
import vn.rikkeisoft.demo.entity.Product;
import vn.rikkeisoft.demo.service.dto.ProductDTO;

import java.sql.Timestamp;

@Component
public class ProductMapper implements EntityMapper<ProductDTO, Product> {
    @Override
    public Product toEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImage(dto.getImage());
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    @Override
    public ProductDTO toDto(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setImage(entity.getImage());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}
