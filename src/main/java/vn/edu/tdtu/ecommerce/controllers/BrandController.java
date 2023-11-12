package vn.edu.tdtu.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.ecommerce.models.Brand;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.services.BrandService;
import vn.edu.tdtu.ecommerce.services.ProductService;

import java.util.List;

@RequestMapping("/admin/brand")
@Controller
public class BrandController {
    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @PostMapping("/add")
    public String addBrand(@RequestParam(value = "brandName") String brandName){
        Brand brand = new Brand();
        brand.setName(brandName);
        brandService.saveBrand(brand);
        return "redirect:/admin/brand";
    }
    @PostMapping("/edit")
    public String updateBrand(@RequestParam(value = "brandName") String brandName,
                              @RequestParam(value = "id") Long id
    ){
        Brand brand = brandService.getBrandById(id);
        brand.setName(brandName);
        brandService.saveBrand(brand);
        return "redirect:/admin/brand";
    }

    @PostMapping("/delete")
    public String deleteBrand(@RequestParam("brandId") Long id){
        Brand foundBrand = brandService.getBrandById(id);
        List<Product> products = foundBrand.getProducts();
        if(products != null){
            products.forEach(product -> {
                product.setBrand(null);
                productService.saveProduct(product);
            });
        }
        brandService.deleteBrand(foundBrand);
        return "redirect:/admin/brand";
    }
}
