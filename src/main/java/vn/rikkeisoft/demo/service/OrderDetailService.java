package vn.rikkeisoft.demo.service;

import vn.rikkeisoft.demo.entity.OrderDetail;
import vn.rikkeisoft.demo.service.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDTO save(OrderDetailDTO dto);
    void delete(Long id);
    List<OrderDetailDTO> findAll();
    OrderDetailDTO findById(Long id);
}
