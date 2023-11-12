package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.Brand;
import vn.edu.tdtu.ecommerce.models.User;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    public boolean existsByName(String name);
}
