package vn.edu.tdtu.ecommerce.services;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.models.Order;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
    public Order getOrderById(Long id){return orderRepository.findById(id).orElse(null);}
    public Order getOrderByUser(User user){
        return orderRepository.findByUser(user);
    }
}
