package vn.edu.tdtu.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.ecommerce.models.Color;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.services.ColorService;
import vn.edu.tdtu.ecommerce.services.ProductService;

import java.util.List;

@RequestMapping("/admin/color")
@Controller
public class ColorController {
    @Autowired
    ColorService colorService;
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public String addColor(@RequestParam(value = "colorName") String colorName){
        Color color = new Color();
        color.setName(colorName);
        colorService.saveColor(color);
        return "redirect:/admin/color";
    }

    @PostMapping("/edit")
    public String updateColor(@RequestParam(value = "colorName") String colorName,
                              @RequestParam(value = "id") Long id
    ){
        Color color = colorService.getColorById(id);
        color.setName(colorName);
        colorService.saveColor(color);
        return "redirect:/admin/color";
    }

    @PostMapping("/delete")
    public String deleteColor(@RequestParam("colorId") Long id){
        Color foundColor = colorService.getColorById(id);
        List<Product> products = foundColor.getProduct();
        if(products != null){
            products.forEach(product -> {
                product.setColor(null);
                productService.saveProduct(product);
            });
        }
        colorService.deleteColor(foundColor);
        return "redirect:/admin/color";
    }
}
