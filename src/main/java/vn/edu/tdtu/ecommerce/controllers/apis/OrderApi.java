package vn.edu.tdtu.ecommerce.controllers.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdtu.ecommerce.dtos.ResponseDTO;
import vn.edu.tdtu.ecommerce.dtos.mapper.Mapper;
import vn.edu.tdtu.ecommerce.dtos.mapper.OrderMapper;
import vn.edu.tdtu.ecommerce.dtos.response.OrderDTO;
import vn.edu.tdtu.ecommerce.models.Order;
import vn.edu.tdtu.ecommerce.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderApi {
    private final OrderService service;

    @GetMapping("")
    public ResponseEntity<?> getAllOrder(){
        List<OrderDTO> orders = service.getAll()
                .stream().map(
                        order -> {
                            Mapper<Order, OrderDTO> mapper = new OrderMapper();
                            return mapper.mapToDTO(order);
                        }
                ).toList();
        return ResponseEntity.ok(
                new ResponseDTO(
                        true,
                        orders
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable long id){
        Mapper<Order, OrderDTO> mapper = new OrderMapper();
        OrderDTO order = mapper.mapToDTO(service.getOrderById(id));

        return ResponseEntity.ok(
                new ResponseDTO(
                        true,
                        order
                )
        );
    }
}
