package vn.edu.tdtu.ecommerce.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.dtos.request.AddCartReqDTO;
import vn.edu.tdtu.ecommerce.models.Cart;
import vn.edu.tdtu.ecommerce.models.CartItem;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.repositories.CartRepository;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CartItemService cartItemService;
    public boolean cartExistByUser(User user){
        Cart cart = cartRepository.findByUser(user).orElse(null);
        return cart != null;
    }

    public Cart getCartByUser(User user){
        return cartRepository.findByUser(user).orElse(null);
    }

    public Cart getCartBySessionToken(String token){
        return cartRepository.findBySessionToken(token).orElse(null);
    }

    public Cart getCartById(Long id){
        return cartRepository.findById(id).orElse(null);
    }

    public boolean addToCart(Principal principal, AddCartReqDTO cart, HttpServletRequest request){
        try{
            Product itemProduct = productService.getProductById(cart.getProductId());
            //Save cart by account
            if(principal != null){
                User user = userService.getUserByUsername(principal.getName());
                Cart foundCart = getCartByUser(user);
                if(foundCart != null){
                    List<Long> itemProductIds = foundCart.getCartItems()
                            .stream().map(item -> item.getProduct().getId()).toList();

                    if(itemProductIds.contains(cart.getProductId())){
                        CartItem existingCartItem = foundCart.getCartItems().
                                stream().filter(item -> item.getProduct().getId().equals(cart.getProductId()))
                                .findFirst()
                                .orElse(new CartItem());
                        existingCartItem.setQuantity(cart.getQuantity() + existingCartItem.getQuantity());
                        cartItemService.saveCartItem(existingCartItem);
                        return true;
                    }else{
                        CartItem item = new CartItem();
                        item.setCart(foundCart);
                        item.setQuantity(cart.getQuantity());
                        item.setProduct(itemProduct);
                        item.setCart(foundCart);
                        cartItemService.saveCartItem(item);
                        return true;
                    }
                }else{
                    foundCart = new Cart();
                    foundCart.setUser(user);
                    CartItem item = new CartItem();
                    item.setProduct(itemProduct);
                    item.setQuantity(cart.getQuantity());
                    item.setCart(foundCart);

                    cartItemService.saveCartItem(item);
                    return true;
                }
            }else{
                if(request.getSession().getAttribute("sessionToken") != null){
                    Cart foundCart = getCartBySessionToken(
                            request.getSession().getAttribute("sessionToken").toString()
                    );
                    if(foundCart != null){
                        List<Long> itemProductIds = foundCart.getCartItems()
                                .stream().map(item -> item.getProduct().getId()).toList();

                        if(itemProductIds.contains(cart.getProductId())){
                            CartItem existingCartItem = foundCart.getCartItems().
                                    stream().filter(item -> item.getProduct().getId().equals(cart.getProductId()))
                                    .findFirst()
                                    .orElse(new CartItem());
                            existingCartItem.setQuantity(cart.getQuantity() + existingCartItem.getQuantity());
                            cartItemService.saveCartItem(existingCartItem);
                            return true;
                        }else{
                            CartItem item = new CartItem();
                            item.setProduct(itemProduct);
                            item.setQuantity(cart.getQuantity());
                            item.setCart(foundCart);
                            cartItemService.saveCartItem(item);
                            return true;
                        }
                    }else{
                        Cart newCart = new Cart();
                        String sessionToken = request.getSession().getAttribute("sessionToken").toString();
                        newCart.setSessionToken(sessionToken);
                        CartItem item = new CartItem();
                        item.setProduct(itemProduct);
                        item.setQuantity(cart.getQuantity());
                        item.setCart(newCart);
                        cartItemService.saveCartItem(item);
                        return true;
                    }
                }else{
                    Cart newCart = new Cart();
                    String sessionToken = UUID.randomUUID().toString();
                    request.getSession().setAttribute("sessionToken", sessionToken);
                    newCart.setSessionToken(sessionToken);
                    CartItem item = new CartItem();
                    item.setProduct(itemProduct);
                    item.setQuantity(cart.getQuantity());
                    item.setCart(newCart);
                    cartItemService.saveCartItem(item);
                    return true;
                }
            }
        }catch (Exception e){
            return false;
        }
    }
}
