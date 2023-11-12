package vn.edu.tdtu.ecommerce.dtos.mapper;

import vn.edu.tdtu.ecommerce.dtos.response.OrderDTO;
import vn.edu.tdtu.ecommerce.models.Order;

public class OrderMapper implements Mapper<Order, OrderDTO> {
    @Override
    public Order mapToObject(OrderDTO dto) {
        return null;
    }

    @Override
    public OrderDTO mapToDTO(Order object) {
        if(object != null){
            OrderDTO dto = new OrderDTO();
            dto.setId(object.getId());
            dto.setName(object.getName());
            dto.setAddress(object.getAddress());
            dto.setOrderDate(object.getOrderDate());
            dto.setEmail(object.getEmail());
            dto.setStatus(object.getStatus().name());
            dto.setPhoneNumber(object.getPhoneNumber());
            dto.setTotal(object.getTotal());
            return dto;
        }
        return null;
    }
}
