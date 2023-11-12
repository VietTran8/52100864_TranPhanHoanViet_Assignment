package vn.edu.tdtu.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.models.Cart;
import vn.edu.tdtu.ecommerce.models.CartItem;
import vn.edu.tdtu.ecommerce.repositories.CartItemRepository;
import vn.edu.tdtu.ecommerce.repositories.CartRepository;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartRepository cartRepository;

    public void saveCartItem(CartItem item){
        cartItemRepository.save(item);
    }
    public boolean updateItemQuantity(long itemId, int quantity){
        CartItem item = cartItemRepository.findById(itemId).orElse(null);
        if(item != null){
            item.setQuantity(quantity);
            cartItemRepository.save(item);
            return true;
        }
        return false;
    }

    public boolean deleteItem(long itemId, long cartId){
        CartItem item = cartItemRepository.findById(itemId).orElse(null);
        Cart foundCart = cartRepository.findById(cartId).orElse(null);
        if(foundCart != null){
            if(item != null){
                foundCart.getCartItems().removeIf(
                        i -> i.getId() == itemId
                );
                cartRepository.save(foundCart);
                cartItemRepository.delete(item);
                return true;
            }
        }
        return false;
    }
}
