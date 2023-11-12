package vn.edu.tdtu.ecommerce.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.repositories.UserRepository;
import vn.edu.tdtu.ecommerce.security.model.UserDetailImp;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Could not found user with username: " + username)
        );
        return UserDetailImp.build(foundUser);
    }
}
