package vn.edu.tdtu.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/")
public class HomeController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ColorService colorService;
    @Autowired
    BrandService brandService;
    @GetMapping({"/home", ""})
    public String home(Model model){
        List<Category> headerCategories = categoryService.getAllCategory();
        List<Brand> headerBrands = brandService.getAllBrand();
        List<Color> headerColors = colorService.getAllColors();
        model.addAttribute("headerCategories", headerCategories);
        model.addAttribute("headerBrands", headerBrands);
        model.addAttribute("headerColors", headerColors);

        List<Product> products = productService.getAllProducts().stream().filter(
                product -> !product.isDeleted()
        ).toList();
        model.addAttribute("products", products);

        return "home";
    }
}
