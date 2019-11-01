package vn.rikkeisoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.rikkeisoft.demo.entity.OrderDetail;
import vn.rikkeisoft.demo.repositories.OrderDetailRepository;
import vn.rikkeisoft.demo.service.dto.OrderDetailDTO;
import vn.rikkeisoft.demo.service.mapper.OrderDetailMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImp implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDetailMapper orderDetailsMapper;

    @Override
    public OrderDetailDTO save(OrderDetailDTO dto) {
        OrderDetail entity = orderDetailsMapper.toEntity(dto);
        orderDetailRepository.save(entity);
        return orderDetailsMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailDTO> findAll() {
        List<OrderDetail> entities = orderDetailRepository.findAll();
        List<OrderDetailDTO> dtos = new ArrayList<>();
        entities.forEach(i -> {
            dtos.add(orderDetailsMapper.toDto(i));
        });
        return dtos;
    }

    @Override
    public OrderDetailDTO findById(Long id) {
        OrderDetail entity = orderDetailRepository.findById(id).get();
        return orderDetailsMapper.toDto(entity);
    }
}
