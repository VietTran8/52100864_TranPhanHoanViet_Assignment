package vn.edu.tdtu.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.models.Brand;
import vn.edu.tdtu.ecommerce.models.Category;
import vn.edu.tdtu.ecommerce.repositories.BrandRepository;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;
    public Brand saveBrand(Brand brand){
        return brandRepository.save(brand);
    }
    public Brand getBrandById(Long id){
        return brandRepository.findById(id).orElse(null);
    }
    public List<Brand> getAllBrand(){
        return brandRepository.findAll();
    }
    public boolean existsByName(String name){
        return brandRepository.existsByName(name);
    }
    public void deleteBrand(Brand brand){
        brandRepository.delete(brand);
    }
}
