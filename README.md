# E-Commerce User Service

User management microservice for an e-commerce application built using **Java, Spring Boot and MySQL**.

This service handles user registration, login, user details and role-based authentication using **JWT**.

## Features

* User registration and login
* JWT-based authentication
* Role-based authorization (`CUSTOMER`, `ADMIN`)
* User details management
* Secure password handling
* REST APIs
* MySQL database integration
* Spring Boot Actuator

## APIs

| API                           | Description             |
| ----------------------------- | ----------------------- |
| `POST /auth/register`         | Register a new customer |
| `POST /auth/login`            | User login              |
| `GET /auth/getuser/{uid}`     | Get user details        |
| `GET /auth/UserDetails/{uid}` | Get user information    |
| `POST /auth/admin/register`   | Register an admin       |

## Technologies

* Java 17
* Spring Boot 3.5.6
* Spring Security
* JWT
* Spring Data JPA
* MySQL
* Maven
* Spring Boot Actuator

## Configuration

Main configuration:

```text
src/main/resources/application.yml
```

Default service port:

```text
8082
```

Example:

```text
http://localhost:8082
```

## Running the Service

Start MySQL first and configure the database details in `application.yml`.

Then run:

```bash
mvn spring-boot:run
```

or on Windows:

```bash
mvnw.cmd spring-boot:run
```

## Project Role

This service is part of the **E-Commerce Microservices Application** and provides authentication and user management for the other services.

## Author

**Deepak Kumar**

Java Backend Developer
