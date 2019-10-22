package vn.rikkeisoft.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "order_num", nullable = false)
    private int orderNum;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_address", nullable = false)
    private String customerAddress;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;
}
