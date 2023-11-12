package vn.edu.tdtu.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartItem> cartItems;
    private String sessionToken;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @Transient
    private double totalPrice;
    @Transient
    private int itemsNumber;

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            if(!item.getProduct().isDeleted())
                total += (item.getQuantity() * item.getProduct().getPrice());
        }
        return total;
    }
    public int getItemsNumber(){
        return cartItems.size();
    }
}
