package vn.rikkeisoft.demo.service.mapper;

import org.springframework.stereotype.Component;
import vn.rikkeisoft.demo.entity.OrderDetail;
import vn.rikkeisoft.demo.entity.Product;
import vn.rikkeisoft.demo.service.dto.OrderDetailDTO;

@Component
public class OrderDetailMapper implements EntityMapper<OrderDetailDTO, OrderDetail> {
    @Override
    public OrderDetail toEntity(OrderDetailDTO dto) {
        OrderDetail entity = new OrderDetail();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setProduct(new Product(dto.getProductId()));
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

    @Override
    public OrderDetailDTO toDto(OrderDetail entity) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setProductId(entity.getProduct().getId());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
}
