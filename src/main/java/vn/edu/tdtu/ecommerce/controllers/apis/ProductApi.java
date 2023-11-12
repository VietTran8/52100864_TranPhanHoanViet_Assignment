package vn.edu.tdtu.ecommerce.controllers.apis;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdtu.ecommerce.dtos.ResponseDTO;
import vn.edu.tdtu.ecommerce.dtos.mapper.Mapper;
import vn.edu.tdtu.ecommerce.dtos.mapper.ProductMapper;
import vn.edu.tdtu.ecommerce.dtos.response.ProductDTO;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService service;
    private final Mapper<Product, ProductDTO> mapper = new ProductMapper();

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        List<ProductDTO> products = service.getAllProducts()
                .stream().map(mapper::mapToDTO).toList();
        return ResponseEntity.ok(
                new ResponseDTO(true, products)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id){
        return ResponseEntity.ok(
                new ResponseDTO(
                        true,
                        mapper.mapToDTO(service.getProductById(id))
                )
        );
    }
}
