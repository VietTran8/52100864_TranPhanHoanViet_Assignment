package vn.edu.tdtu.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean deleted;
    private String name;
    private Double price;
    private String image;
    @Column(length = 100000)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brandId")
    private Brand brand;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colorId")
    private Color color;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartItem> items;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
