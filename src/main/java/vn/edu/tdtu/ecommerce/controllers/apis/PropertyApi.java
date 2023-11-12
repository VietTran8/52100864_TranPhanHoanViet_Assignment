package vn.edu.tdtu.ecommerce.controllers.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdtu.ecommerce.dtos.ResponseDTO;
import vn.edu.tdtu.ecommerce.services.BrandService;
import vn.edu.tdtu.ecommerce.services.CategoryService;
import vn.edu.tdtu.ecommerce.services.ColorService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PropertyApi {
    private final ColorService colorService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @GetMapping("/color")
    public ResponseEntity<?> getAllColors(){
        return ResponseEntity.ok(
                new ResponseDTO(
                    true,
                        colorService.getAllColors()
                )
        );
    }

    @GetMapping("/brand")
    public ResponseEntity<?> getAllBrands(){
        return ResponseEntity.ok(
                new ResponseDTO(
                        true,
                        brandService.getAllBrand()
                )
        );
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(
                new ResponseDTO(
                        true,
                        categoryService.getAllCategory()
                )
        );
    }
}
