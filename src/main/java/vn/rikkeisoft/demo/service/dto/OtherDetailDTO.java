package vn.rikkeisoft.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.rikkeisoft.demo.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherDetailDTO {

    private long id;
    private Product product;
    private int quantity;
    private double price;

}
