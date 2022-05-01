# REST API Demo
Java Spring Boot Rest API Application

# Endpoints

## Root Endpoint
localhost:8080

## User Endpoint
There are two types of roles:
- ADMIN
- USER

When updating or adding roles to User, it is case sensitive. So All-Caps.

### GET
- /users
- /users/{id}

### POST
- /users
- include payload i.e
    
    ```
    {
      "firstName": "Joe",
      "lastName": "Caputo",
      "email": "joe@email.com",
      "password": "joe@123",
      "roles": ["USER", "ADMIN"]
    }
    ```

### PUT
- /users/{id}
- include payload i.e

    ```
    {
      "email": "joeKnoe@gmail.com",
      "roles": ["USER"]
    }
    ```
- the other fields can be updated as well

### DELETE
- /users/{id}

