package vn.edu.tdtu.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartReqDTO {
    private int quantity;
    private long itemId;
    private long cartId;
}
