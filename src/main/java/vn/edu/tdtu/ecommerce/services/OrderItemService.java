package vn.edu.tdtu.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.models.OrderItem;
import vn.edu.tdtu.ecommerce.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    public OrderItem saveOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
