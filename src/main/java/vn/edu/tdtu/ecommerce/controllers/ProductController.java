package vn.edu.tdtu.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.ecommerce.models.Brand;
import vn.edu.tdtu.ecommerce.models.Category;
import vn.edu.tdtu.ecommerce.models.Color;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.services.BrandService;
import vn.edu.tdtu.ecommerce.services.CategoryService;
import vn.edu.tdtu.ecommerce.services.ColorService;
import vn.edu.tdtu.ecommerce.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ColorService colorService;
    @Autowired
    BrandService brandService;
    @GetMapping("/{id}")
    public String detail(@PathVariable(value = "id") Long id, Model model){
        Product foundProduct = productService.getProductById(id);
        if(foundProduct != null){
            if(foundProduct.isDeleted())
                return "redirect:/home";
            model.addAttribute("product", foundProduct);
        }else{
            return "redirect:/home";
        }
        return "detail";
    }

    @GetMapping("/search-result")
    public String searchResult(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "brand") String brand,
            @RequestParam(value = "category") String category,
            @RequestParam(value = "color") String color,
            Model model
    ){
        List<Category> headerCategories = categoryService.getAllCategory();
        List<Brand> headerBrands = brandService.getAllBrand();
        List<Color> headerColors = colorService.getAllColors();
        model.addAttribute("headerCategories", headerCategories);
        model.addAttribute("headerBrands", headerBrands);
        model.addAttribute("headerColors", headerColors);
        model.addAttribute("products", productService.getByFilter(
                keyword, brand, category, color
        ).stream().filter(product -> !product.isDeleted()).toList());
        return "search-result";
    }
}
