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
- **/users**
  - status code `200 OK`
- i.e response

```json
{
    "_embedded": {
        "userList": [
            {
                "id": 1,
                "firstName": "Quinn",
                "lastName": "Frost",
                "email": "quinn@email.com",
                "password": "password@123",
                "createdAt": "2022-05-02T17:18:10",
                "modifiedAt": null,
                "roles": [
                    {
                        "id": 1,
                        "roleName": "ADMIN"
                    },
                    {
                        "id": 2,
                        "roleName": "USER"
                    }
                ],
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/users/1"
                    },
                    "users": {
                        "href": "http://localhost:8080/api/users"
                    }
                }
            },
        ]
    },
    "...more objects": null,
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/users"
        }
    }
}
```

- **/users/{id}**
  - status code `200 OK`
- i.e response

```json
{
    "id": 1,
    "firstName": "Quinn",
    "lastName": "Frost",
    "email": "quinn@email.com",
    "password": "password@123",
    "createdAt": "2022-05-02T17:18:10",
    "modifiedAt": null,
    "roles": [
        {
            "id": 1,
            "roleName": "ADMIN"
        },
        {
            "id": 2,
            "roleName": "USER"
        }
    ],
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/users/1"
        },
        "users": {
            "href": "http://localhost:8080/api/users"
        }
    }
}
```

### POST
- **/users**
  - status code `201 CREATED`
- include payload i.e
    
```json
{
  "firstName": "Joe",
  "lastName": "Caputo",
  "email": "joe@email.com",
  "password": "joe@123",
  "roles": ["USER", "ADMIN"]
}
```

### PUT
- **/users/{id}**
  - status code `201 CREATED`
- include payload i.e

```json
{
  "email": "joeKnoe@gmail.com",
  "roles": ["USER"]
}
```
- i.e response
```json
{
    "id": 4,
    "firstName": "Joe",
    "lastName": "Caputo",
    "email": "joeKnoe@gmail.com",
    "password": "joe@123",
    "createdAt": "2022-05-02T17:18:10",
    "modifiedAt": "2022-05-02T17:44:42.67138138",
    "roles": [
        {
            "id": 2,
            "roleName": "USER"
        }
    ],
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/users/4"
        },
        "users": {
            "href": "http://localhost:8080/api/users"
        }
    }
}
```
- the other fields can be updated as well

### DELETE
- **/users/{id}**
  - status code `204 No Content`
- i.e response

```json
1
```

