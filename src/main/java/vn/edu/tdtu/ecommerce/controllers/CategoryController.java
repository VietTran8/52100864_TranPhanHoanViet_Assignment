package vn.edu.tdtu.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.ecommerce.models.Category;
import vn.edu.tdtu.ecommerce.models.Color;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.services.CategoryService;
import vn.edu.tdtu.ecommerce.services.ProductService;

import java.util.List;

@RequestMapping("/admin/category")
@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public String addCategory(@RequestParam(value = "categoryName") String categoryName){
        Category category = new Category();
        category.setName(categoryName);
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }
    @PostMapping("/edit")
    public String updateCategory(@RequestParam(value = "categoryName") String categoryName,
                                 @RequestParam(value = "id") Long id
    ){
        Category category = categoryService.getCategoryById(id);
        category.setName(categoryName);
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("categoryId") Long id){
        Category foundCategory = categoryService.getCategoryById(id);
        List<Product> products = foundCategory.getProducts();
        if(products != null){
            products.forEach(product -> {
                product.setCategory(null);
                productService.saveProduct(product);
            });
        }
        categoryService.deleteCategory(foundCategory);
        return "redirect:/admin/category";
    }
}
