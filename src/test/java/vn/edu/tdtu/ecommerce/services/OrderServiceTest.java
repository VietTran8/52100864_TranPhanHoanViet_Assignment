package vn.edu.tdtu.ecommerce.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vn.edu.tdtu.ecommerce.enums.EPaidStatus;
import vn.edu.tdtu.ecommerce.enums.EUserRole;
import vn.edu.tdtu.ecommerce.models.Order;
import vn.edu.tdtu.ecommerce.models.OrderItem;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    private List<Order> orderListExpected;
    private Order orderExpected;

    @Before
    public void setUp(){
        User user = new User();
        user.setUsername("viettran08");
        user.setRole(EUserRole.ROLE_USER);

        List<OrderItem> items = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            OrderItem item = new OrderItem();
            item.setQuantity(2 + i);

            Product product = new Product();
            product.setPrice(5123.0);

            item.setProduct(product);
            items.add(item);
        }

        orderExpected = new Order();
        orderExpected.setStatus(EPaidStatus.STATUS_PAID);
        orderExpected.setName("Phan Hoan Viet");
        orderExpected.setUser(user);
        orderExpected.setOrderItems(items);
        orderExpected.setAddress("TP HCM");
        orderExpected.setEmail("viettran03@gmail.com");
        orderExpected.setPhoneNumber("0123456789");
        orderExpected.setId(1L);

        Order order2 = new Order();
        order2.setStatus(EPaidStatus.STATUS_PAID);
        order2.setName("Viet Tran");
        order2.setUser(user);
        order2.setOrderItems(items);
        order2.setAddress("Quan 7, TP HCM");
        order2.setEmail("viettran04@gmail.com");
        order2.setPhoneNumber("0123456780");
        order2.setId(2L);

        orderListExpected = new ArrayList<>();
        orderListExpected.add(orderExpected);
        orderListExpected.add(order2);

        Mockito.when(orderRepository.findAll())
                .thenReturn(orderListExpected);

        Mockito.when(orderRepository.findById(1L))
                .thenReturn(Optional.of(orderExpected));

        Mockito.when(orderRepository.findByUser(user))
                .thenReturn(orderExpected);
    }

    @Test
    public void getAllOrdersTest(){
        List<Order> ordersResult = orderService.getAll();
        Assert.assertEquals(orderListExpected, ordersResult);
    }

    @Test
    public void getOrderByIdTest(){
        Order orderResult = orderService.getOrderById(1L);
        Assert.assertEquals(orderExpected, orderResult);
    }

    @Test
    public void getOrderByUser(){
        User user = new User();
        user.setUsername("viettran08");
        user.setRole(EUserRole.ROLE_USER);
        Order orderResult = orderService.getOrderByUser(user);

        Assert.assertEquals(orderExpected, orderResult);
    }

    @Test
    public void saveOrderTest(){
        orderService.saveOrder(orderExpected);
        Mockito.verify(orderRepository).save(orderExpected);
    }
}
