package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.*;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public boolean existsByName(String name);
}
