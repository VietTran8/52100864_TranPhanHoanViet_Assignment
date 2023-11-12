package vn.edu.tdtu.ecommerce.dtos.mapper;

import vn.edu.tdtu.ecommerce.dtos.response.ProductDTO;
import vn.edu.tdtu.ecommerce.models.Product;

public class ProductMapper implements Mapper<Product, ProductDTO> {
    @Override
    public Product mapToObject(ProductDTO dto) {
        return null;
    }

    @Override
    public ProductDTO mapToDTO(Product object) {
        if(object != null){
            ProductDTO dto = new ProductDTO();
            dto.setId(object.getId());
            dto.setName(object.getName());
            dto.setColor(object.getColor().getName());
            dto.setBrand(object.getBrand().getName());
            dto.setImage(object.getImage());
            dto.setDescription(object.getDescription());
            dto.setPrice(object.getPrice());
            dto.setCategory(object.getCategory().getName());
            return dto;
        }
        return null;
    }
}
