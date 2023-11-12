# Midterm Assignment


<h1 align="center">
  <br>
  <img src="https://user-images.githubusercontent.com/114124106/229993734-aca974ee-a58b-4452-86e9-8cf94bfad6df.png" alt="Markdownify" width="600px">
  <br>
  52100864 - Trần Phan Hoàn Việt
  <br>
</h1>

<h3 align="center">Very Simple Spring E-Commerce Application</h4>
<br>

## Principles and technologies used

* Principles Used:
    - Single Responsibility (Each class should only have one responsibility. Furthermore, it should only have one reason to change).
    - Spring MVC:
        + Model: serves as the core component responsible for managing the application's data and business logic.
        + View: is responsible for presenting the data from the Model to the user and handling user interface interactions. It represents the visual and interactive elements of the application. The View receives information from the Model and displays it to the user in a way that is appropriate for the chosen platform or user interface.
        + Controller: this is the layer that communicates with the outside and handles requests from outside to the system.
        + Service Layer: Perform operations and handle logic.
        + Repository Layer: Responsible for communicating with DBs, storage devices, query processing, and returning data types that the Service layer requires.
* Technologies Used:
    - Spring Boot (Maven project)
    - MySQL (Database)
    - Xampp (Connect to DB)
    - Cloudinary (Store multipart files)
    - Thymeleaf (Java template engine for processing and creating HTML, XML, JavaScript, CSS and text).
    - Spring Security (For authentication and authorization)
      + In this assignment, I used Spring security to assign permissions for the admin and user. That is, the object logged in as an admin, can access the admins pages such as adding, deleting, and editing products (product management). Admin will also manage the orders that customers have paid before (order management).
      + If the logged-in object is a customer then they can see the product list. If the customer finds a product that they like, they can view its
          details and add it to their shopping cart and proceed to place an order.


## Project Structure
* Project overview image:
  <h1 align="center">
    <br>
      <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699765990/assignment/project-structure_cwg2qh.png" alt="Markdownify" width="600px">
  </h1>
* Folder Details
    - Folder **config**: contains some configuration classes needed for application such as:
      + Cloudinary config: included api and secret key to access the remote file storage.
      + Database config: automatically generated data in the database upon the initial execution of the application.
      + WebMvcConfig: configure web page redirect when accessing the path is not allowed.
      + WebSecurityConfig: The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration. Perform the function of decentralization for users and admins.
    - Folder **models**: is the place where all the entity tables are stored and created and stored directly in the Database.
    - Folder **controllers**: methods are the entry point that a http request can reach. After being invoked, the controller method starts to process the web request by interacting with the service layer to complete the work that needs to be done.
    - Folder **services**: includes methods for performing system functions, interacting directly with the controller.
    - Folder **repositories**: responsible for communicating with DBs, storage devices, query processing, and returning data types requested by the Service layer.
    - Folder **security**: included 
      + Security user model (user detail) to store user information which is later encapsulated into Authentication objects. 
      + User service (user detail service) is used by DaoAuthenticationProvider for retrieving a username, a password, and other attributes for authenticating with a username and password. Spring Security provides in-memory and JDBC implementations of UserDetailsService. This class implement from UserDetailsService.
    - Folder **dtos**: contains all data transfer objects to response to client or receive data from client.
    - Folder **enums**: includes special data types that represent fixed set of constants to define a collection of related constant values, which are represented a finite set of choices.
    - Folder **exception**: incorporates a class responsible for managing the 'no handler found' exception (404 status code). In the absence of a handler to process a user's request, this class redirects them to the homepage.
    - Moreover, the project contains a Resources Folder, comprising two subfolders. Specifically, the Static folder stores static resources, while the Templates Folder encompasses HTML files related to the user interface, errors, custom errors, and a page for unauthorized access.

## Spring Security Filter
![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699802247/assignment/security_iv10wf.png)

## Admin Permission
- With the absolute security and decentralization of Spring Security. Admin can access all the links of the system.

<h4 align="center">
    <br>
      <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699766373/assignment/admin-page_c4ti9m.png" alt="image" width="100%">
    <br>
</h4>

- This account management page is specifically designed to manage administrative accounts:

![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699771160/assignment/account-manage_xkbjh7.png)
![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699771160/assignment/brand-manage_leihl2.png)

- Category, color management page are the same with brand management page.
- Order management is just about viewing a list of orders or details of an order from customers and updating the status of these orders:

![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699771446/assignment/order-manage_koywdy.png)
## Registered User Permission
- In contrast to admin, users who registered can only access permitted paths, not allowed to access paths located in ROLE ADMIN
- All path have "/admin/**" that users are not allowed to access
- If users attempt to access the admin path without the necessary permissions, the no-permission page will be displayed:
  <h4 align="center">
  <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699772050/assignment/no-permission_upvlnp.png" alt="image" width="100%">
  <br>
  </h4>


### And below are some pages that both ROLE user and admin can access:
- Homepage:
<h4 align="center">
      <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699772211/assignment/homepage_m4d4lh.png" alt="image" width="100%">
    <br>
</h4>
- Product details:
<h4 align="center">
      <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699772304/assignment/product-details_a7pc1h.png" alt="image" width="100%">
    <br>
</h4>
- Shopping cart:
<h4 align="center">
      <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699772395/assignment/shopping-cart_uq4ada.png" alt="image" width="100%">
    <br>
</h4>
- Your orders:
<h4 align="center">
  <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699772531/assignment/your-orders_xvem3k.png" alt="image" width="100%">
  <br>
</h4>
> **Note:**
> There are many other pages within the system, and certain pages are exclusively administered by administrators, allowing access only to administrators. These pages include functionalities such as adding, deleting, and editing products, as well as overseeing product management and order details. To gain a firsthand experience of the system, you can follow the steps outlined in the **How To Build And Run Project** section!

## Guest User Permission
- This permission is like **Registered user permission**, but do not allow to access the order history page (*/order/history*).
- The guest's shopping cart is simply stored in a session.
## How To Build And Run Project

There are steps to clone and run this application:
- **Step 1**: Clone this repository:
```bash
$ git clone https://github.com/VietTran8/52100864_TranPhanHoanViet_Assignment.git
```
- **Step 2:** Open the project that has recently been cloned using IntelliJ IDEA.
- **Step 3:** Run MySQL service from XAMPP or Docker container.
- **Step 4:** Create database named **"ecommerce"**.
- **Step 5:** From IntelliJ, RUN project and then enjoy!

> **Note:**
> The data will be automatically inserted into the database on the first run, eliminating the need for manual actions such as importing SQL scripts.

### Default accounts:
| Username | Password |   Role    |
|:--------:|:--------:|:---------:|
| admin123 |  admin   |  `ADMIN`  |
| user123  |   user   |  `USER`   |

> **Note**
> Make sure you have fully implemented the steps listed above to be able to successfully run the project.

## Some APIs

### APIs
#### Product API

| Method   | URL                                       | Description               |
| -------- | ----------------------------------------  |---------------------------|
| `GET`    | `/api/product`                            | Retrieve all products.    |
| `GET`    | `/api/product/1`                          | Retrieve product by ID 1. |

#### Order API

| Method   | URL                                       | Description                              |
| -------- | ----------------------------------------  | ---------------------------------------- |
| `GET`    | `/api/order`                              | Retrieve all orders.                     |
| `GET`    | `/api/order/1`                            | Retrieve order by ID 1.                  |

#### Category API

| Method   | URL                                       | Description                             |
| -------- | ----------------------------------------  | --------------------------------------- |
| `GET`    | `/api/category`                           | Retrieve all categories.                |
| `GET`    | `/api/category/1`                         | Retrieve category by ID 1.              |             

#### Brand API

| Method   | URL                                       | Description                              |
| -------- | ----------------------------------------  | ---------------------------------------- |
| `GET`    | `/api/brand`                              | Retrieve all brands.                     |
| `GET`    | `/api/brand/1`                            | Retrieve brand by ID 1.                  |             

#### Color API

| Method   | URL                                       | Description                              |
| -------- | ----------------------------------------  | ---------------------------------------- |
| `GET`    | `/api/color`                              | Retrieve all colors.                     |
| `GET`    | `/api/color/1`                            | Retrieve color by ID 1.                  |             

### Postman Snapshots:
#### Product API
![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699781104/assignment/get-all-product_cl6ltp.png)
![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699781104/assignment/get-product-by-id_hoyush.png)

#### Order API
![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699781104/assignment/get-order-all_mkeyxl.png)
![image](https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699781104/assignment/get-order-by-id_ocdqhp.png)

> **Note**
> Do the same for the remaining request. The APIs are already written in the code, to avoid long lines I didn't insert any images. Thank you so much !!


## Unit Test using JUnit and Mockito
Within this project, my objective is to devise test cases for specific services, including UserService, ProductService, and OrderService.
<h4 align="center">
    <br>
        <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699799781/assignment/test-folder_ubx98z.png" alt="image" width="60%">
    <br>
</h4>

### ProductServiceTest
- I have success 4 testcase for this Service test. Details in the code:
  <h4 align="left">
  <br>
  <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699800062/assignment/product-test-result_l2khql.png" alt="image" width="300px">
  <br>
  </h4>

### OrderServiceTest
- I have success 4 testcase for this Service test. Details in the code:
  <h4 align="left">
  <br>
  <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699800167/assignment/order-testcase-result_exvlmz.png" alt="image" width="300px">
  <br>
  </h4>

### UserServiceTest
- I have success 5 testcase for this Service test. Details in the code:
  <h4 align="left">
  <br>
  <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699800167/assignment/user-testcase-result_mcelop.png" alt="image" width="300px">
  <br>
  </h4>


## Diagram
### Entity-relationship diagram
<h2 align="center">
    <br>
      <img src="https://res.cloudinary.com/dwv7gmfcr/image/upload/v1699801776/assignment/ERDDiagram_xxypyo.jpg" alt="image" width="100%">
    <br>
  </h2>

## References
- [Security with Spring](https://www.baeldung.com/security-spring)
- [Spring boot home](https://spring.io/)
- [Thymeleaf documentation](https://www.thymeleaf.org/documentation.html)
- [Restfull API Tutorials](https://www.tutorialspoint.com/spring_boot/spring_boot_building_restful_web_services.html)
- Lab lessons taught by Mr. Vo Van Thanh