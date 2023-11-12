package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.Color;
import vn.edu.tdtu.ecommerce.models.User;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    public boolean existsByName(String name);
}
