package vn.edu.tdtu.ecommerce.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.tdtu.ecommerce.enums.EPaidStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Date orderDate;
    private double total;
    private String status;
}
