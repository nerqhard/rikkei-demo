package vn.rikkeisoft.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.rikkeisoft.demo.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private long id;
    private Long productId;
    private int quantity;
    private double price;


}
