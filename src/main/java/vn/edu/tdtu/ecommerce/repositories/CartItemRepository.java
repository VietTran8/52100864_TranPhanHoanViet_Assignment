package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
