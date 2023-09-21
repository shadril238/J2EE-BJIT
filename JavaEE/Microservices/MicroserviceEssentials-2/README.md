# Product
### add product
```
curl --location --request POST 'http://localhost:8082/api/product/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productName":"iphone 8",
    "productSKU":"zxc",
    "productDescription":"released on 2020",
    "productPrice":80000,
    "productWeight":200,
    "productCategory" : "phone"
}'
```

### get
```
curl --location --request GET 'http://localhost:8081/auth/api/product/list'\
```

### delete
```
curl --location --request DELETE 'http://localhost:8081/auth/api/product/delete/2'\
```

### update

```
curl --location --request POST 'http://localhost:8081/auth/api/product/update' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId":2,
    "productName":"iphone 13",
    "productSKU":"zxc",
    "productDescription":"released on 2020",
    "productPrice":80000,
    "productWeight":200,
    "productCategory" : "phone"
}'
```

### updateQuantity
```
curl --location --request POST 'http://localhost:8082/api/product/updateQuantity' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productRequests": [
        {
            "productId": 1,
            "qty": 6
        },
        {
            "productId": 2,
            "qty": 7
        }
    ]
}'


### revokeQuantity

```
curl --location --request POST 'http://localhost:8082/api/product/revokeQuantity' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productRequests": [
        {
            "productId": 1,
            "qty": 6
        },
        {
            "productId": 2,
            "qty": 7
        }
    ]
}'
```



# Config Server

```
curl --location --request GET 'http://localhost:8888/config-server/default'
```


### post order through api gateway
```
curl --location --request POST 'http://localhost:9090/orderapp/api/order/submit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "shippingMethod": "Dhaka Couriar",
    "totalPrice": 500.5,
    "productList": [
        {
            "productId": 1,
            "qty": 5
        },
        {
            "productId": 2,
            "qty": 10
        }
    ]
}'
```



### Get Order list
```
curl --location --request GET 'http://localhost:8083/api/order/list'
```

```
curl --location --request GET 'http://localhost:9090/orderapp/api/order/list'
```

Eureka: http://localhost:8761/
API Gateway: 
Zipkin: https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec

java -jar zipkin-server-2.12.9-exec.jar 

0:0:0:0:0:0:0:0%0:9411 - http://127.0.0.1:9411/



















mysql config : https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04