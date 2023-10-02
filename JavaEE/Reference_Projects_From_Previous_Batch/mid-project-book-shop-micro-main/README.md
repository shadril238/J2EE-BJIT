# Mid Term Project
## Online Book Shop Micro-Service Project
<hr>
<br>

## Team URSA
<hr>

### Team Members
- Sajal Halder
- Md. Rahat Ibna Hossain
- Md. Ariful Islam
- Ullash Bhattacharjee

<br>

## Setup Environment
<hr>

- JDK 17
- Gradle 8.1.1
- Sprint Boot 2.7.12
  

<br> 

## Database Details
<hr>

- MySql Server Port : 8585
- MySql Username : root
- MySql Password : root
- Authentication Database Name : auth_db
- Book Service Database Name : book_db
- Inventory Service Database Name : inventory_db

<br>


## Additional Details
<hr>

- Zipkin Port: 9411


<br>

## Functionalities
<hr>

<br>

-  Service 01: API Gateway
 
     You need to follow API gateway architecture here. We will not directly communicate with the individual micro-services. All the communication will happen via the API gateway. In the API gateway users (ADMIN and USER) need to authenticate themselves before accessing the other micro-services.

     <br>

- Service 02: Discover Server
 
     Along with API gateway, you need to use discovery server as a service. This discover service will be responsible for identifying the available service instances of our application.

     <br>

- Service 03: Cloud Config Server
 
     The configuration properties of all the micro-services will be stored in the cloud (GitHub repo). We will fetch the configuration properties via this cloud config server.

     <br>

- Service 04: Authentication Service
 
   This service will be responsible for authenticating the user and generate access token. Later this token will be used to access other micro services. ADMIN and USER both can use this service to authenticate them.selves This service will have the below APIs:

   - /auth-server/register: Both USER and ADMIN will use this API to register them.
   - /auth-server/login: Both USER and ADMIN will use this API for login. After login, this API will return them a JWT token. Later which will be used in the API gateway for accessing the micro-services.

     <br>

- Service 05: Book Service
 
    ADMIN and USER both can use this service. This service will have the below APIs:

    - /book-service/create: Only ADMIN will use this API to insert new book information to the DB. While calling this API, ADMIN will pass book name, author name, genre, price and quantity in the request body. Among of these fields, book name, author name and genre will be saved to the DB by this service. To store price and quantity, you need to call Service 06: Book Inventory Service.
    - /book-service/update: Only ADMIN will use this API to update book information to the DB. This API will also call Service 06: Book Inventory Service to update price and quantity information.
    - /book-service/delete: Only ADMIN will use this API to delete book from the DB. This API will call Service 06: Book Inventory Service to delete price and quantity information for that particular book.
    - /book-service/book/all: Both ADMIN and USER can use this API to fetch all the book data from DB. This API will call Service 06: Book Inventory Service to fetch price and quantity information for the books.
    - /book-service/book/{id}: Both ADMIN and USER can use this API to fetch a single book data from DB. This API will also call Service 06: Book Inventory Service to fetch price and quantity information for that book.
    - /book-service/book/buy: Only USER can use this API to buy books from the online book store. In the request body USER need to pass book id and quantity for the desired book that he/she wants to purchase. After successful purchase, this API will call Service 06: Book Inventory Service to update the quantity information for that book.

     <br>

- Service 06: Book Inventory

    This micro-service will not be directly called by users. We will call it from Service 05: Book Service. This micro-service will have the below APIs:

    - /book-inventory/update/book-id: This API will be used to UPDATE the price and quantity information of a particular book.
    - /book-inventory/book-id: This API will be used to FETCH the price and quantity information of a particular book.
    - /book-inventory: This API will take a list of book ids, and then FETCH the price and quantity information for those books.
    - /book-inventory/delete/book-id: This API will be used to DELETE the price and quantity information of a particular book.


<br>

## API EndPoints
<hr>

- /auth-server/register

   - Method : POST  
   - Full Path 
    
     `http://localhost:8080/auth-server/register`

  - Input 
  
   

    ` {
    "email":"rahatibnhossain@gmail.com",
    "password":"1234",
    "roles": ["ADMIN"]
    } `

  - Output For Successful Database Operation
  
    ` { "data": {
        "message": "Successfully registered with the  email rahatibnhossain@gmail.com."
    } }`

  - Output For Unsuccessful Database Operation
  
    ` {
    "error_message": "The requested email rahatibnhossain@gmail.com already registered"             
     }`

<br>

- /auth-server/login

   - Method : POST  
   - Full Path 
    
     `http://localhost:8080/auth-server/login`


  - Input 

    ` {
    "email":"rahatibnhossain@gmail.com",
    "password":"1234",
    "roles": ["ADMIN"]
    } `

  - Output For Successful Login
  
    `{
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJBRE1JTiIsIlVTRVIiXSwic3ViIjoicmFoYXRpYm5ob3NzYWluQGdtYWlsLmNvbSIsImlhdCI6MTY4NjE5NDYyOSwiZXhwIjoxNjg2MTk2MDY5fQ.XS-nJvncEOwbkZAlgNGWO0-BhZFEYZ-6-duS5AZXnkE"
    }
}`

  - Output For Unsuccessful Login
  
    `{
    "error_message": "Invalid Email and Password"
     }`

<br>


- /book-service/create
  


   - Method : POST  
   - Full Path 
    
     `http://localhost:8080/book-service/create`


  - Input 

    ` {
     "bookName": "The Fire 2",
     "authorName": "Rahat",
     "genre": "Paris Times",
    "price": 20.0,
    "quantity":4
    }`

  - Output For Successful Database Operation
  
    `{
    "data": {
        "book_id": 1,
        "bookName": "The Fire 2",
        "authorName": "Rahat",
        "genre": "Paris Times",
        "price": 20.0,
        "quantity": 4
    }
    }`

  - Output For Unsuccessful Database Operation
    
    When This book already exists
    
    `{
    "error_message": "A book with the same name and author already exists."
    }`

    When Inventory Service is Unavailable 
    
    `{
    "error_message": "Inventory Service is Unavailable"
    }`

    

     <br>

- /book-service/update

   - Method : PUT  
   - Full Path 
    
     `http://localhost:8080/book-service/update`

  - Input 

    ` {
        "book_id": 4,
        "bookName": "The Fire 6",
        "authorName": "Rahat Ibna Hossain",
        "genre": "Paris Times",
        "price": 20.0,
        "quantity": 4 }`

  - Output For Successful Update Book Operation
  
    `{
    "data": {
        "book_id": 4,
        "bookName": "The Fire 6",
        "authorName": "Rahat Ibna Hossain",
        "genre": "Paris Times",
        "price": 20.0,
        "quantity": 4
    }}`

  - Output For Unsuccessful Update Book Operation
  
    When Book Id is invalid

    `{
    "error_message": "Book not found" }`

    
    When Inventory Service is Unavailable

    `{
    "error_message": "Inventory Service is Unavailable"
    }`


<br>

- /book-service/delete

   - Method : DELETE  
   - Full Path 
    
     `http://localhost:8080/book-service/delete{bookId}`

  - Output For Successful Delete Book Operation
  
    `{
    "data": "Successfully deleted"
    }`

  - Output For Unsuccessful Delete Book Operation
  
    When Book Id is invalid

    `{
    "error_message": "Book not found" }`

    
    When Inventory Service is Unavailable

    `{
    "error_message": "Inventory Service is Unavailable"
    }`


<br>

- /book-service/book/all

   - Method : GET  
   - Full Path 
    
     `http://localhost:8080/book-service/book/all`



  - Output For Successful Getting All Books Operation
  
    `{
    "data": [
        {
            "book_id": 1,
            "bookName": "The Fire 2",
            "authorName": "Rahat",
            "genre": "Paris Times",
            "price": 20.0,
            "quantity": 4
        },
        {
            "book_id": 2,
            "bookName": "The Fire 3",
            "authorName": "Rahat",
            "genre": "Paris Times",
            "price": 20.0,
            "quantity": 4
        }
    ]   
    }`

  - Output For Unsuccessful Getting All Books Operation
  
      When Inventory Service is Unavailable

    `{
    "error_message": "Inventory Service is Unavailable"
    }`

<br>







- /book-service/book/id/{BookId}

   - Method : GET  
   - Full Path 
    
     `http://localhost:8080/book-service/book/id/2`



  - Output For Successful Getting Single Book Operation
  
    `{
    "data": {
        "book_id": 2,
        "bookName": "The Fire 3",
        "authorName": "Rahat",
        "genre": "Paris Times",
        "price": 20.0,
        "quantity": 4
    }
}`

  - Output For Unsuccessful Getting Single Book Operation
  
  
    When Book Id is invalid

    `{
    "error_message": "Book not found" }`

    
    When Inventory Service is Unavailable

    `{
    "error_message": "Inventory Service is Unavailable"
    }`



- /book-service/book/buy

   - Method : POST  
   - Full Path 
    
     `http://localhost:8080/book-service/book/buy`

  - Input 

    ` {
        "book_id": 2,
        "quantity": 2
    }`

  - Output For Successful Update Book Operation
  
    `{
    "data": "Buy book operation successful" }`

  - Output For Unsuccessful Update Book Operation
  
    When Book Quantity Exists the Stock Limit

    `{
    "error_message": "Exceed quantity" }`

    
    When Inventory Service is Unavailable

    `{
    "error_message": "Inventory Service is Unavailable"
    }`



<br>



- /inventory-service/update/{bookId}

   - Method : PUT  
   - Full Path 
    
     `http://localhost:8080/inventory-service/update/1`

  - Input 

    ` { "bookPrice": 20.0,
        "bookQuantity": 4 }`

  - Output For Successful Update Inventory Operation
  
    `{ "data": {
        "bookId": 1,
        "bookPrice": 20.0,
        "bookQuantity": 4
    }}`

  - Output For Unsuccessful Update Inventory Operation
  
    When Book Id is invalid

    `{
    "error_message": "Not found" }`



<br>


- /inventory-service/{bookId}

   - Method : GET  
   - Full Path 
    
     `http://localhost:8080/inventory-service/1`



  - Output For Successful Get Single Inventory Operation
  
    `{ "data": {
        "bookId": 1,
        "bookPrice": 20.0,
        "bookQuantity": 4
    }}`

  - Output For Unsuccessful Get Single Inventory Operation
  
    When Book Id is invalid

    `{
    "error_message": "Not Found" }`



<br>


- /inventory-service/

   - Method : POST  
   - Full Path 
    
     `http://localhost:8080/inventory-service`


  - Input 

    ` [1,2]`

  - Output For Successful Get Multiple Inventory Operation
  
    `{
    "data": [
        {
            "bookId": 1,
            "bookPrice": 20.0,
            "bookQuantity": 4
        },
        {
            "bookId": 2,
            "bookPrice": 20.0,
            "bookQuantity": 2
        }
    ]}`

  - Output For Unsuccessful Get Multiple Inventory Operation
  
    When Book Id is invalid

    `{
    "data": [] }`



<br>