package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.Cart;
import vn.edu.tdtu.ecommerce.models.User;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    public Optional<Cart> findByUser(User user);
    public Optional<Cart> findBySessionToken(String token);
}
