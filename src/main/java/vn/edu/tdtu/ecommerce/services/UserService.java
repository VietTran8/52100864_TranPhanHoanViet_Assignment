package vn.edu.tdtu.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.enums.EUserRole;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User saveUser(User user){
        return userRepository.save(user);
    }

    public boolean userIsExistByUsername(String username){
        User user = userRepository.findByUsername(username).orElse(null);
        return user != null;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<User> getAllUserByRole(EUserRole role){
        return userRepository.findByRole(role);
    }
    public void disableAdminAccount(Long id){
        User foundUser = userRepository.findById(id).orElse(null);
        if(foundUser != null){
            foundUser.setActive(false);
            userRepository.save(foundUser);
        }
    }

    public void enableAdminAccount(Long id){
        User foundUser = userRepository.findById(id).orElse(null);
        if(foundUser != null){
            foundUser.setActive(true);
            userRepository.save(foundUser);
        }
    }
}
