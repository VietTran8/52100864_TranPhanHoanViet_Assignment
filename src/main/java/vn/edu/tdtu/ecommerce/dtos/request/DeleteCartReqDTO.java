package vn.edu.tdtu.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCartReqDTO {
    private long itemId;
    private long cartId;
}
