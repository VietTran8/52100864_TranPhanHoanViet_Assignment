package vn.edu.tdtu.ecommerce.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vn.edu.tdtu.ecommerce.models.Brand;
import vn.edu.tdtu.ecommerce.models.Category;
import vn.edu.tdtu.ecommerce.models.Color;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository repository;
    private Product expectedProduct;
    private List<Product> productListExpected;
    @Before
    public void setUp(){
        expectedProduct = new Product();
        expectedProduct.setName("Iphone 13");
        expectedProduct.setId(1L);
        expectedProduct.setPrice(1000.0);
        expectedProduct.setBrand(new Brand());
        expectedProduct.setImage("image1");
        expectedProduct.setCategory(new Category());
        expectedProduct.setColor(new Color());
        expectedProduct.setDescription("This is a description");
        expectedProduct.setDeleted(false);

        Product newProduct = new Product();
        newProduct = new Product();
        newProduct.setName("Iphone 15");
        newProduct.setId(2L);
        newProduct.setPrice(1011.0);
        newProduct.setBrand(new Brand());
        newProduct.setImage("image2");
        newProduct.setCategory(new Category());
        newProduct.setColor(new Color());
        newProduct.setDescription("This is a description 2");
        newProduct.setDeleted(false);

        productListExpected = new ArrayList<>();

        productListExpected.add(newProduct);
        productListExpected.add(expectedProduct);

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(expectedProduct));

        Mockito.when(repository.findAll())
                .thenReturn(productListExpected);
    }

    @Test
    public void getProductByIdTest(){
        Product productResult = productService.getProductById(1L);
        Assert.assertEquals(expectedProduct, productResult);
    }

    @Test
    public void getAllProductsTest(){
        List<Product> productsResult = productService.getAllProducts();
        Assert.assertEquals(productListExpected, productsResult);
    }

    @Test
    public void saveProductTest(){
        Product product = new Product();
        product.setId(3L);
        product.setName("Samsung");

        product.setCategory(new Category());
        product.setColor(new Color());
        product.setBrand(new Brand());

        productService.saveProduct(product);
        Mockito.verify(repository).save(product);
    }

    @Test
    public void existsByNameTest(){
        productService.existsByName("Iphone 13");
        Mockito.verify(repository).existsByName("Iphone 13");
    }
}
