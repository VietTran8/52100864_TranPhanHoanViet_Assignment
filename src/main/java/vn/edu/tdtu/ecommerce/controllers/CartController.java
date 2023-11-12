package vn.edu.tdtu.ecommerce.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.ecommerce.dtos.ResponseDTO;
import vn.edu.tdtu.ecommerce.dtos.request.AddCartReqDTO;
import vn.edu.tdtu.ecommerce.dtos.request.DeleteCartReqDTO;
import vn.edu.tdtu.ecommerce.dtos.request.UpdateCartReqDTO;
import vn.edu.tdtu.ecommerce.models.*;
import vn.edu.tdtu.ecommerce.services.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ColorService colorService;
    @Autowired
    BrandService brandService;
    @Autowired
    CartItemService cartItemService;
    @GetMapping
    public String cartPage(Model model, Principal principal, HttpServletRequest request){
        List<Category> headerCategories = categoryService.getAllCategory();
        List<Brand> headerBrands = brandService.getAllBrand();
        List<Color> headerColors = colorService.getAllColors();
        model.addAttribute("headerCategories", headerCategories);
        model.addAttribute("headerBrands", headerBrands);
        model.addAttribute("headerColors", headerColors);

        //Get cart by session
        if(principal == null){
            if(request.getSession().getAttribute("sessionToken") != null){
                String sessionToken = request.getSession().getAttribute("sessionToken").toString();
                Cart foundCart = cartService.getCartBySessionToken(sessionToken);
                if(foundCart != null){
                    model.addAttribute("cartItems", foundCart.getCartItems());
                }else{
                    model.addAttribute("cartItems", new ArrayList<>());
                }
                model.addAttribute("cart", foundCart);
                return "cart";
            }
            model.addAttribute("cartItems", new ArrayList<>());
            return "cart";
        }
        //Get cart by user account
        Cart foundCart = cartService.getCartByUser(userService.getUserByUsername(principal.getName()));
        if(foundCart != null){
            model.addAttribute("cartItems", foundCart.getCartItems());
            model.addAttribute("cart", foundCart);
            return "cart";
        }
        model.addAttribute("cartItems", new ArrayList<>());
        model.addAttribute("cart", foundCart);
        return "cart";
    }
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addCart(Principal principal,
                                               @RequestBody AddCartReqDTO cart,
                                               HttpServletRequest request){

        return ResponseEntity.ok(
               new ResponseDTO(cartService.addToCart(principal, cart, request), null)
        );
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateCart(@RequestBody UpdateCartReqDTO request){
        boolean isOk = cartItemService.updateItemQuantity(
                request.getItemId(),
                request.getQuantity()
        );
        double total = cartService.getCartById(request.getCartId()).getTotalPrice();
        Map<String, Double> response = new HashMap<>();
        response.put("total", total);
        return ResponseEntity.ok(
                new ResponseDTO(isOk, response)
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCart(@RequestBody DeleteCartReqDTO request){
        boolean isOk = cartItemService.deleteItem(request.getItemId(), request.getCartId());
        return ResponseEntity.ok(
                new ResponseDTO(isOk, null)
        );
    }
}
