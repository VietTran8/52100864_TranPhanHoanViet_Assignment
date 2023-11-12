package vn.edu.tdtu.ecommerce.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String name;
    private double price;
    private String image;
    private String description;
    private String brand;
    private String color;
    private String category;
}
