package vn.edu.tdtu.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.ecommerce.enums.EPaidStatus;
import vn.edu.tdtu.ecommerce.models.*;
import vn.edu.tdtu.ecommerce.services.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ColorService colorService;
    @Autowired
    BrandService brandService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    @PostMapping("/checkout")
    public String checkout(
            @RequestParam("cartId") Long cartId,
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            Model model
    ){
        Cart cart = cartService.getCartById(cartId);
        Order order = new Order();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate;
        if(cart != null){
            order.setOrderDate(new Date());
            order.setName(fullName);
            order.setEmail(email);
            order.setAddress(address);
            order.setPhoneNumber(phone);
            order.setStatus(EPaidStatus.STATUS_NOT_PAID);
            order.setUser(cart.getUser());
            order.setTotal(cart.getTotalPrice());
            cart.getCartItems().forEach(
                item -> {
                    if(!item.getProduct().isDeleted()){
                        OrderItem orderItem = new OrderItem();
                        orderItem.setProduct(item.getProduct());
                        orderItem.setQuantity(item.getQuantity());
                        orderItem.setOrder(order);
                        orderItemService.saveOrderItem(orderItem);
                    }
                }
            );
            model.addAttribute("isSuccess", true);
            model.addAttribute("savedOrder", order);

            formattedDate = simpleDateFormat.format(order.getOrderDate());
        }else{
            model.addAttribute("isSuccess", false);
            model.addAttribute("savedOrder", null);
            formattedDate = "";
        }
        model.addAttribute("orderedDate", formattedDate);
        return "checkout";
    }

    @GetMapping("/checkout")
    public String checkoutPage(){
        return "redirect:/home";
    }

    @GetMapping("/history")
    public String orderHistory(Principal principal, Model model){
        List<Category> headerCategories = categoryService.getAllCategory();
        List<Brand> headerBrands = brandService.getAllBrand();
        List<Color> headerColors = colorService.getAllColors();
        model.addAttribute("headerCategories", headerCategories);
        model.addAttribute("headerBrands", headerBrands);
        model.addAttribute("headerColors", headerColors);

        User user = userService.getUserByUsername(principal.getName());
        List<Order> orders = user.getOrders();
        if( orders == null){
            orders = new ArrayList<>();
        }
        model.addAttribute("orders", orders);
        return "order-history";
    }
}
