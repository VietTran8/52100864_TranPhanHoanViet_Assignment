package vn.edu.tdtu.ecommerce.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vn.edu.tdtu.ecommerce.enums.EUserRole;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    private User expectedUser;
    private List<User> expectedUserList;

    @Before
    public void setUp(){
        expectedUser = new User();
        expectedUser.setRole(EUserRole.ROLE_ADMIN);
        expectedUser.setId(1L);
        expectedUser.setUsername("viettran03");
        expectedUser.setOrders(new ArrayList<>());
        expectedUser.setCarts(new ArrayList<>());
        expectedUser.setActive(true);

        User user1 = new User();
        user1.setRole(EUserRole.ROLE_ADMIN);
        user1.setId(2L);
        user1.setUsername("viettran04");
        user1.setOrders(new ArrayList<>());
        user1.setCarts(new ArrayList<>());
        user1.setActive(true);

        expectedUserList = new ArrayList<>();
        expectedUserList.add(user1);
        expectedUserList.add(expectedUser);


        Mockito.when(userRepository.findByUsername("viettran03"))
                .thenReturn(Optional.ofNullable(expectedUser));

        Mockito.when(userRepository.findAll())
                .thenReturn(expectedUserList);

        Mockito.when(userRepository.findByRole(EUserRole.ROLE_ADMIN))
                .thenReturn(expectedUserList);

    }

    @Test
    public void saveUserTest(){
        User user = new User();
        userService.saveUser(user);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void userIsExistsByUsernameTest(){
        userService.userIsExistByUsername("Viettran");
        Mockito.verify(userRepository).findByUsername("Viettran");
    }

    @Test
    public void getUserByUsernameTest(){
        userService.getUserByUsername("viettran03");
        Mockito.verify(userRepository).findByUsername("viettran03");
    }

    @Test
    public void getAllUserTest(){
        List<User> usersResult = userService.getAllUser();
        Assert.assertEquals(expectedUserList, usersResult);
    }

    @Test
    public void getAllUserByRole(){
        List<User> usersResult = userService.getAllUserByRole(EUserRole.ROLE_ADMIN);
        Assert.assertEquals(expectedUserList, usersResult);
    }
}
