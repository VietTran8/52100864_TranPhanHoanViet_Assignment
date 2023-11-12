package vn.edu.tdtu.ecommerce.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.edu.tdtu.ecommerce.enums.EUserRole;
import vn.edu.tdtu.ecommerce.models.Brand;
import vn.edu.tdtu.ecommerce.models.Category;
import vn.edu.tdtu.ecommerce.models.Color;
import vn.edu.tdtu.ecommerce.models.Product;
import vn.edu.tdtu.ecommerce.models.User;
import vn.edu.tdtu.ecommerce.services.*;

@Configuration
public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ColorService colorService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdminAccount() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (!userService.userIsExistByUsername("admin123")) {
                    User defaultAdmin = new User();
                    defaultAdmin.setUsername("admin123");
                    defaultAdmin.setPassword(passwordEncoder.encode("admin"));
                    defaultAdmin.setRole(EUserRole.ROLE_ADMIN);
                    defaultAdmin.setActive(true);
                    userService.saveUser(defaultAdmin);
                    logger.info("Inserted default admin to database: " + defaultAdmin.toString());
                }

                if (!userService.userIsExistByUsername("user123")) {
                    User defaultUser = new User();
                    defaultUser.setUsername("user123");
                    defaultUser.setPassword(passwordEncoder.encode("user"));
                    defaultUser.setRole(EUserRole.ROLE_USER);
                    defaultUser.setActive(true);
                    userService.saveUser(defaultUser);
                    logger.info("Inserted default user to database: " + defaultUser.toString());
                }

                Product product1 = new Product();
                Product product2 = new Product();
                Product product3 = new Product();
                Product product4 = new Product();
                Product product5 = new Product();
                Product product6 = new Product();

                if (!brandService.existsByName("Apple") &&
                        !brandService.existsByName("Samsung") &&
                        !brandService.existsByName("Lenovo") &&
                        !brandService.existsByName("Xiaomi") &&
                        !brandService.existsByName("Acer")) {
                    Brand brand1 = new Brand();
                    brand1.setName("Apple");
                    Brand brand2 = new Brand();
                    brand2.setName("Samsung");
                    Brand brand3 = new Brand();
                    brand3.setName("Lenovo");
                    Brand brand4 = new Brand();
                    brand4.setName("Xiaomi");
                    Brand brand5 = new Brand();
                    brand5.setName("Acer");

                    product1.setBrand(brand1);
                    product2.setBrand(brand1);
                    product3.setBrand(brand2);
                    product4.setBrand(brand4);
                    product5.setBrand(brand5);
                    product6.setBrand(brand2);

                    logger.info("Added default brand to database: " + brandService.saveBrand(brand1));
                    logger.info("Added default brand to database: " + brandService.saveBrand(brand2));
                    logger.info("Added default brand to database: " + brandService.saveBrand(brand3));
                    logger.info("Added default brand to database: " + brandService.saveBrand(brand4));
                    logger.info("Added default brand to database: " + brandService.saveBrand(brand5));
                }

                if (!colorService.existsByName("White") &&
                        !colorService.existsByName("Red") &&
                        !colorService.existsByName("Blue") &&
                        !colorService.existsByName("Gray") &&
                        !colorService.existsByName("Black")) {
                    Color color1 = new Color();
                    color1.setName("White");
                    Color color2 = new Color();
                    color2.setName("Red");
                    Color color3 = new Color();
                    color3.setName("Blue");
                    Color color4 = new Color();
                    color4.setName("Gray");
                    Color color5 = new Color();
                    color5.setName("Black");

                    product1.setColor(color1);
                    product2.setColor(color1);
                    product3.setColor(color2);
                    product4.setColor(color4);
                    product5.setColor(color5);
                    product6.setColor(color2);

                    logger.info("Added default color to database: " + colorService.saveColor(color1));
                    logger.info("Added default color to database: " + colorService.saveColor(color2));
                    logger.info("Added default color to database: " + colorService.saveColor(color3));
                    logger.info("Added default color to database: " + colorService.saveColor(color4));
                    logger.info("Added default color to database: " + colorService.saveColor(color5));
                }

                if (!categoryService.existsByName("Laptop") &&
                        !categoryService.existsByName("Smartphone") &&
                        !categoryService.existsByName("Tablet") &&
                        !categoryService.existsByName("Smartwatch") &&
                        !categoryService.existsByName("Camera")) {
                    Category category1 = new Category();
                    category1.setName("Laptop");
                    Category category2 = new Category();
                    category2.setName("Smartphone");
                    Category category3 = new Category();
                    category3.setName("Smartwatch");
                    Category category4 = new Category();
                    category4.setName("Camera");
                    Category category5 = new Category();
                    category5.setName("Tablet");

                    product1.setCategory(category2);
                    product2.setCategory(category2);
                    product3.setCategory(category2);
                    product4.setCategory(category2);
                    product5.setCategory(category1);
                    product6.setCategory(category2);

                    logger.info("Added default category to database: " + categoryService.saveCategory(category1));
                    logger.info("Added default category to database: " + categoryService.saveCategory(category2));
                    logger.info("Added default category to database: " + categoryService.saveCategory(category3));
                    logger.info("Added default category to database: " + categoryService.saveCategory(category4));
                    logger.info("Added default category to database: " + categoryService.saveCategory(category5));
                }
                
                if(
                    !productService.existsByName("Iphone 11") &&
                    !productService.existsByName("Iphone 14 promax") &&
                    !productService.existsByName("Samsung Galaxy Z Series") &&
                    !productService.existsByName("Xiaomi 13 pro") &&
                    !productService.existsByName("Acer Laptop Aspire 5 Intel Core i7 13th Gen 1355U (1.70GHz) 16 GB LPDDR5 Memory 512 GB PCIe SSD Intel Iris Xe Graphics 15.6\" Windows 11 Home 64-bit A515-58M-78JL") &&
                    !productService.existsByName("Galaxy Z Fold 5")
                ){
                    product1.setName("Iphone 11");
                    product2.setName("Iphone 14 promax");
                    product3.setName("Samsung Galaxy Z Series");
                    product4.setName("Xiaomi 13 pro");
                    product5.setName("Acer Laptop Aspire 5 Intel Core i7 13th Gen 1355U (1.70GHz) 16 GB LPDDR5 Memory 512 GB PCIe SSD Intel Iris Xe Graphics 15.6\" Windows 11 Home 64-bit A515-58M-78JL");
                    product6.setName("Galaxy Z Fold 5");

                    product1.setImage("https://res.cloudinary.com/dwv7gmfcr/image/upload/v1698225196/iphone11_v1y7si.webp");
                    product2.setImage("https://res.cloudinary.com/dwv7gmfcr/image/upload/v1698225196/iphone14promax_vtrpvv.jpg");
                    product3.setImage("https://res.cloudinary.com/dwv7gmfcr/image/upload/v1698225193/galaxyzflip_dbaadc.jpg");
                    product4.setImage("https://res.cloudinary.com/dwv7gmfcr/image/upload/v1698225192/xiaomi_slhart.jpg");
                    product5.setImage("https://res.cloudinary.com/dwv7gmfcr/image/upload/v1698225192/Acer-Aspire-5_vaamfc.webp");
                    product6.setImage("https://res.cloudinary.com/dwv7gmfcr/image/upload/v1698225191/Samsung--Galaxy-Fold-5G-761_aifrwk.webp");

                    product1.setDescription("The iPhone 11 is a smartphone designed, developed, and marketed by Apple Inc. It is the thirteenth generation of iPhone, succeeding the iPhone XR, and was unveiled on September 10, 2019, alongside the higher-end iPhone 11 Pro at the Steve Jobs Theater in Apple Park, Cupertino, by Apple CEO Tim Cook.");
                    product2.setDescription("The iPhone 14 Pro and iPhone 14 Pro Max are smartphones designed, developed, and marketed by Apple Inc. They are the sixteenth-generation flagship iPhones, succeeding the iPhone 13 Pro and iPhone 13 Pro Max. ");
                    product3.setDescription("The Samsung Galaxy Z series (named as Samsung Galaxy Foldables in certain territories) is a line of foldable smartphones manufactured by Samsung Electronics. With the 2020 announcement of the Galaxy Z Flip, Samsung's future foldable smartphones will be part of the Galaxy Z series.");
                    product4.setDescription("The Xiaomi 13 Pro is Xiaomi's latest flagship smartphone featuring top-of-the-line specs and performance. It has a 6.73-inch AMOLED screen with a 3200x1440 resolution and 120Hz refresh rate, along with a punch-hole cutout for the 32MP front camera. Powering the device is the powerful Qualcomm Snapdragon 8 Gen 2 chipset along with up to 12GB of fast LPDDR5X RAM and 512GB of UFS 4.0 storage.");
                    product5.setDescription("The Acer Aspire 5 is a mid-range laptop aimed at students, professionals and casual users. It has a 15.6-inch full HD IPS display with thin bezels for an immersive viewing experience. Powering the device is an 11th generation Intel Core i7-1165G7 processor, which is one of Intel's powerful quad-core CPUs for thin-and-light laptops.");
                    product6.setDescription("The Samsung Galaxy Z Fold 5 is expected to be the next generation foldable flagship from Samsung, building upon the success of the Z Fold 4. Rumors suggest it will feature larger internal and external AMOLED displays compared to its predecessor.");

                    product1.setPrice(820.0);
                    product2.setPrice(1000.0);
                    product3.setPrice(1200.0);
                    product4.setPrice(790.0);
                    product5.setPrice(1500.0);
                    product6.setPrice(1200.0);

                    product1.setDeleted(false);
                    product2.setDeleted(false);
                    product3.setDeleted(false);
                    product4.setDeleted(false);
                    product5.setDeleted(false);
                    product6.setDeleted(false);

                    logger.info("Added default product to database: " + productService.saveProduct(product1));
                    logger.info("Added default product to database: " + productService.saveProduct(product2));
                    logger.info("Added default product to database: " + productService.saveProduct(product3));
                    logger.info("Added default product to database: " + productService.saveProduct(product4));
                    logger.info("Added default product to database: " + productService.saveProduct(product5));
                    logger.info("Added default product to database: " + productService.saveProduct(product6));
                }
            }
        };
    }
}
