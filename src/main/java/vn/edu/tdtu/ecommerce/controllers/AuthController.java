package vn.edu.tdtu.ecommerce.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.ecommerce.enums.EUserRole;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.services.UserService;

import java.security.Principal;


@Controller
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, Principal principal) {
        if(principal == null){
            String message = null;
            HttpSession session = request.getSession(false);
            if(session != null){
                AuthenticationException ex = (AuthenticationException) session.getAttribute(
                        WebAttributes.AUTHENTICATION_EXCEPTION
                );
                if(ex != null){
                    message = ex.getMessage();
                }
            }
            model.addAttribute("errorMessage", message);
            return "login";
        }
        System.out.println(principal.getName());
        return "redirect:/home";
    }

    @GetMapping("/error")
    public String error(){
        return "home";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        if(userService.userIsExistByUsername(username)){
            return "error/signup-error";
        }
        User user = new User();
        user.setActive(true);
        user.setRole(EUserRole.ROLE_USER);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/admin/account/add")
    public String saveAdminUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        if(userService.userIsExistByUsername(username)){
            return "redirect:/admin/account/add?error=true";
        }
        User user = new User();
        user.setActive(true);
        user.setRole(EUserRole.ROLE_ADMIN);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);
        return "redirect:/admin/account";
    }
}
