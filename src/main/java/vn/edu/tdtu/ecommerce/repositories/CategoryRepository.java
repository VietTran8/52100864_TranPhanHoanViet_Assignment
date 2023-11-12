package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.Category;
import vn.edu.tdtu.ecommerce.models.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public boolean existsByName(String name);
}
