package vn.edu.tdtu.ecommerce.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionsHandler{
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handler404(NoHandlerFoundException ex){
        return "redirect:/home";
    }
}
