package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.ecommerce.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
