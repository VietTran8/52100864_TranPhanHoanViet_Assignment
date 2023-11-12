package vn.edu.tdtu.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartReqDTO {
    private Long productId;
    private int quantity;
}
