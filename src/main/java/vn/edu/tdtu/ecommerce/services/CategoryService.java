package vn.edu.tdtu.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.models.Category;
import vn.edu.tdtu.ecommerce.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public boolean existsByName(String name){
        return categoryRepository.existsByName(name);
    }
    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }

}
