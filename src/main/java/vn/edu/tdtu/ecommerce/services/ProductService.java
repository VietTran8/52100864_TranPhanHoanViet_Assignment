package vn.edu.tdtu.ecommerce.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import vn.edu.tdtu.ecommerce.dtos.request.AddCartReqDTO;
import vn.edu.tdtu.ecommerce.models.*;
import vn.edu.tdtu.ecommerce.repositories.ProductCustomRepository;
import vn.edu.tdtu.ecommerce.repositories.ProductRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCustomRepository productCustomRepository;

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getByFilter(String keyword, String brand, String category, String color){
        return productCustomRepository.getProductByFilter(keyword, brand, color, category);
    }
    public boolean existsByName(String name){
        return productRepository.existsByName(name);
    }
}
