package vn.edu.tdtu.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.ecommerce.models.Color;
import vn.edu.tdtu.ecommerce.repositories.ColorRepository;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    ColorRepository colorRepository;
    public List<Color> getAllColors(){
        return colorRepository.findAll();
    }
    public Color getColorById(Long id){
        return colorRepository.findById(id).orElse(null);
    }
    public Color saveColor(Color color){
        return colorRepository.save(color);
    }
    public boolean existsByName(String name){
        return colorRepository.existsByName(name);
    }
    public void deleteColor(Color color){
        colorRepository.delete(color);
    }
}
