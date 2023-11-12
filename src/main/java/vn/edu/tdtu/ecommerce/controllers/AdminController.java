package vn.edu.tdtu.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.ecommerce.enums.EPaidStatus;
import vn.edu.tdtu.ecommerce.enums.EUserRole;
import vn.edu.tdtu.ecommerce.models.*;
import vn.edu.tdtu.ecommerce.services.*;
import vn.edu.tdtu.ecommerce.services.cloudinary.FileUploadServiceImpl;

import java.io.IOException;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ColorService colorService;
    @Autowired
    ProductService productService;
    @Autowired
    FileUploadServiceImpl fileService;
    @Autowired
    OrderService orderService;
    @GetMapping
    public String adminPage(){
        return "redirect:/admin/product";
    }

    @GetMapping("/{page}")
    public String managementPage(Model model, @PathVariable("page") String page){
        List<Category> headerCategories = categoryService.getAllCategory();
        List<Brand> headerBrands = brandService.getAllBrand();
        List<Color> headerColors = colorService.getAllColors();
        model.addAttribute("headerCategories", headerCategories);
        model.addAttribute("headerBrands", headerBrands);
        model.addAttribute("headerColors", headerColors);

        model.addAttribute("page", page);
        model.addAttribute("action", null);
        if(page.equals("account")){
            List<User> users = userService.getAllUserByRole(EUserRole.ROLE_ADMIN);
            model.addAttribute("users", users);
        }else if(page.equals("brand")){
            List<Brand> brands = brandService.getAllBrand();
            model.addAttribute("brands", brands);
        }else if(page.equals("category")){
            List<Category> categories = categoryService.getAllCategory();
            model.addAttribute("categories", categories);
        }else if(page.equals("color")){
            List<Color> colors = colorService.getAllColors();
            model.addAttribute("colors", colors);
        }else if(page.equals("product")){
            List<Product> products = productService.getAllProducts();
            products = products.stream().filter(prod -> !prod.isDeleted()).toList();
            model.addAttribute("products", products);
        }else if(page.equals("order")){
            List<Order> orders = orderService.getAll();
            model.addAttribute("orders", orders);
        }
        return "admin/admin";
    }

    @GetMapping("/{page}/{action}")
    public String managementPageWithAction(Model model,
                          @PathVariable("page") String page,
                          @PathVariable("action") String action,
                          @RequestParam(value = "id", required = false) Long id){
        model.addAttribute("page", page);
        model.addAttribute("action", action);
        if(page.equals("product")){
            model.addAttribute("colors", colorService.getAllColors());
            model.addAttribute("brands", brandService.getAllBrand());
            model.addAttribute("categories", categoryService.getAllCategory());
            if(action.equals("edit")){
                Product foundProduct = productService.getProductById(id);
                model.addAttribute("product", foundProduct);
            }
        }
        if(action.equals("detail") && page.equals("product")){
            Product foundProduct = productService.getProductById(id);
            model.addAttribute("product", foundProduct);
        }
        if(action.equals("edit") && page.equals("brand")){
            Brand foundBrand = brandService.getBrandById(id);
            model.addAttribute("brand", foundBrand);
        }
        if(action.equals("edit") && page.equals("color")){
            Color foundColor = colorService.getColorById(id);
            model.addAttribute("color", foundColor);
        }
        if(action.equals("edit") && page.equals("category")){
            Category foundCategory = categoryService.getCategoryById(id);
            model.addAttribute("category", foundCategory);
        }
        if((action.equals("detail") || action.equals("edit")) && page.equals("order")){
            Order foundOrder = orderService.getOrderById(id);
            model.addAttribute("order", foundOrder);
            if(action.equals("edit"))
                model.addAttribute("isPaid",
                        foundOrder.getStatus().name().equals("STATUS_PAID") ? 0 : 1);
        }
        return "admin/admin";
    }
    @PostMapping("/product/add")
    public String addProduct(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "price") double price,
            @RequestParam(value = "image") MultipartFile image,
            @RequestParam(value = "color") Long colorId,
            @RequestParam(value = "category") Long categoryId,
            @RequestParam(value = "brand") Long brandId,
            @RequestParam(value = "description") String description
    ){
        Color color = null;
        Category category = null;
        Brand brand = null;
        if(colorId != -1)
            color = colorService.getColorById(colorId);
        if(categoryId != -1)
            category = categoryService.getCategoryById(categoryId);
        if(brandId != -1)
            brand = brandService.getBrandById(brandId);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        String imgSrc = null;
        try {
            imgSrc = fileService.uploadFile(image);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        product.setImage(imgSrc);
        product.setDescription(description);
        product.setBrand(brand);
        product.setColor(color);
        product.setCategory(category);
        product.setDeleted(false);

        productService.saveProduct(product);
        return "redirect:/admin/product";
    }
    @PostMapping("/product/edit")
    public String editProduct(
            @RequestParam(value = "productId") Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "price") double price,
            @RequestParam(value = "image") MultipartFile image,
            @RequestParam(value = "color") Long colorId,
            @RequestParam(value = "category") Long categoryId,
            @RequestParam(value = "brand") Long brandId,
            @RequestParam(value = "description") String description
    ){
        Product foundProduct = productService.getProductById(id);
        if(foundProduct != null){
            Color color = null;
            Category category = null;
            Brand brand = null;
            if(colorId != -1)
                color = colorService.getColorById(colorId);
            if(categoryId != -1)
                category = categoryService.getCategoryById(categoryId);
            if(brandId != -1)
                brand = brandService.getBrandById(brandId);

            String imgSrc = null;
            if(!image.getOriginalFilename().isEmpty()){
                try {
                    imgSrc = fileService.uploadFile(image);
                    foundProduct.setImage(imgSrc);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            foundProduct.setName(name);
            foundProduct.setPrice(price);
            foundProduct.setDescription(description);
            foundProduct.setBrand(brand);
            foundProduct.setColor(color);
            foundProduct.setCategory(category);
            foundProduct.setDeleted(false);

            productService.saveProduct(foundProduct);
        }
        return "redirect:/admin/product";
    }

    @PostMapping("/product/delete")
    public String deleteProduct(@RequestParam("productId") Long id){
        Product foundProduct = productService.getProductById(id);
        foundProduct.setDeleted(true);

        productService.saveProduct(foundProduct);
        return "redirect:/admin/product";
    }

    @PostMapping("/order/edit")
    public String updateOrderStatus(
            @RequestParam("id") Long orderId,
            @RequestParam("status") String status
    ){
        Order foundOrder = orderService.getOrderById(orderId);
        switch (status){
            case "STATUS_PAID" -> foundOrder.setStatus(EPaidStatus.STATUS_PAID);
            case "STATUS_NOT_PAID" -> foundOrder.setStatus(EPaidStatus.STATUS_NOT_PAID);
        }
        orderService.saveOrder(foundOrder);
        return "redirect:/admin/order";
    }

    @PostMapping("/account/disable")
    public String disableAccount(@RequestParam("accountId") Long id){
        userService.disableAdminAccount(id);
        return "redirect:/admin/account";
    }

    @PostMapping("/account/enable")
    public String enableAccount(@RequestParam("accountId") Long id){
        userService.enableAdminAccount(id);
        return "redirect:/admin/account";
    }
}
