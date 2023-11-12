package vn.edu.tdtu.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.enums.EUserRole;
import vn.edu.tdtu.ecommerce.models.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
    public List<User> findByRole(EUserRole role);
}
