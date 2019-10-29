package vn.rikkeisoft.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long id;

    @NotEmpty(message = "Not to empty!")
    private String code;

    @NotEmpty(message = "Not to empty!")
    private String name;

    @NotEmpty(message = "Not to empty!")
    private double price;

    private byte[] image;

    private Date createDate;
}
