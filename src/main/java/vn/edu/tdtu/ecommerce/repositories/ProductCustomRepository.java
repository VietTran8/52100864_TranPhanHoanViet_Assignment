package vn.edu.tdtu.ecommerce.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.ecommerce.models.Product;

import java.util.List;

@Repository
public class ProductCustomRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Product> getProductByFilter(String keyword, String brand, String color, String category){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        Predicate predicateKeyword = builder.like(root.get("name"), "%" + keyword + "%");
        Predicate predicateBrand = builder.like(root.get("brand").get("name"), "%" + brand + "%");
        Predicate predicateColor = builder.like(root.get("color").get("name"), "%" + color + "%");
        Predicate predicateCategory = builder.like(root.get("category").get("name"), "%" + category + "%");

        criteriaQuery.select(root).where(predicateKeyword, predicateBrand, predicateColor, predicateCategory);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
