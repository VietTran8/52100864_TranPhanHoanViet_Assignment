package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.Order;
import vn.edu.tdtu.ecommerce.models.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findByUser(User user);
}
