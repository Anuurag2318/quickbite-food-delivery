# QuickBite - Event-Driven Food Delivery Backend

## Overview
QuickBite is a backend food delivery application built using Spring Boot and PostgreSQL. 
The project is being developed incrementally, starting with a monolithic architecture and later evolving into a microservices-based, event-driven system using Kafka, Redis, and Docker.
---

## Tech Stack

* Java 17
* Spring Boot 3.5.15
* Spring Web
* Spring Data JPA
* Spring Security
* JWT Authentication
* PostgreSQL
* Maven
* Lombok
* Kafka (Upcoming)
* Redis (Upcoming)
* Docker (Upcoming)

---

## Current Features

### User Management

* User Registration
* User Login
* BCrypt Password Encryption
* JWT Token Generation
* JWT Authentication & Authorization 
* Protected APIs 
* Role-Based User Model

### Restaurant Management
*  Add Restaurant 
* Get Restaurant by ID 
* Get All Restaurants 
* Update Restaurant 
* Delete Restaurant 
* Global Exception Handling

### Food Item Management
* Add Food Item 
* Get Food Item by ID 
* Get All Food Items 
* Update Food Item 
* Delete Food Item 
* Restaurant ↔ FoodItem Relationship (One-To-Many)

### Order Management
* Place Order 
* OrderItem Entity 
* Order ↔ OrderItem Relationship 
* FoodItem ↔ OrderItem Relationship 
* Automatic Total Amount Calculation 
* Historical Price Storage 
* Order Status Management (PLACED)
---

## Database Schema

### Users

| Column   | Type               |
| -------- | ------------------ |
| id       | Long               |
| name     | String             |
| email    | String (Unique)    |
| password | String (Encrypted) |
| role     | Enum               |

### Restaurants

| Column  | Type   |
| ------- | ------ |
| id      | Long   |
| name    | String |
| address | String |

---

### Food Items

| Column        | Type      |
| ------------- | --------- |
| id            | Long      |
| name          | String    |
| description   | String    |
| price         | Double    |
| restaurant_id | Long (FK) |

---

### Orders

| Column       | Type          |
| ------------ | ------------- |
| id           | Long          |
| order_date   | LocalDateTime |
| total_amount | Double        |
| status       | Enum          |
| user_id      | Long (FK)     |

---

### Order Items

| Column       | Type      |
| ------------ | --------- |
| id           | Long      |
| quantity     | Integer   |
| price        | Double    |
| food_item_id | Long (FK) |
| order_id     | Long (FK) |

---

## API Endpoints

### Register User

POST /auth/register

Request:

{
"name": "Raj",
"email": "[Raj@gmail.com](mailto:Raj@gmail.com)",
"password": "password123"
}

Response:

{
"id": 1,
"name": "Raj",
"email": "[Raj@gmail.com](mailto:Raj@gmail.com)",
"role": "CUSTOMER"
}

### Login User
POST /auth/login

Request:

{
"email": "Raj@gmail.com",
"password": "password123"
}

Response:

{
"token": "eyJhbGciOiJIUzI1NiJ9..."
}
---

### Restaurant APIs

* **POST** `/restaurants`
* **GET** `/restaurants`
* **GET** `/restaurants/{id}`
* **PUT** `/restaurants/{id}`
* **DELETE** `/restaurants/{id}`

---

### Food Item APIs

* **POST** `/food-items`
* **GET** `/food-items`
* **GET** `/food-items/{id}`
* **PUT** `/food-items/{id}`
* **DELETE** `/food-items/{id}`

---

### Order APIs

#### Place Order

**POST** `/orders`

Sample Request

```json
{
  "userId": 1,
  "items": [
    {
      "foodItemId": 1,
      "quantity": 2
    },
    {
      "foodItemId": 2,
      "quantity": 1
    }
  ]
}
```

Sample Response

```json
{
  "id": 1,
  "totalAmount": 837.0,
  "status": "PLACED"
}
```

---

## Architecture Implemented

```text
Controller Layer
        │
        ▼
Service Layer
        │
        ▼
Repository Layer
        │
        ▼
PostgreSQL Database
```
---

## Authentication Flow

```text
User Login
    │
    ▼
JWT Token Generated
    │
    ▼
Client Stores Token
    │
    ▼
Client Sends Token in Authorization Header
    │
    ▼
JwtAuthenticationFilter Intercepts Request
    │
    ▼
JWT Token Validation
    │
    ▼
Extract User Email
    │
    ▼
Load User Details From Database
    │
    ▼
Store Authentication In SecurityContextHolder
    │
    ▼
Access Protected API
```
## Order Processing Flow

```text
Client Places Order
        │
        ▼
Validate User
        │
        ▼
Validate Food Items
        │
        ▼
Create Order
        │
        ▼
Calculate Total Amount
        │
        ▼
Create Order Items
        │
        ▼
Save Order
        │
        ▼
Return Order Response
```

### Authentication Process

1. User logs in using email and password.
2. Password is verified using BCrypt.
3. A JWT token is generated and returned to the client.
4. The client sends the token in the Authorization header for protected APIs.
5. JwtAuthenticationFilter validates the token.
6. User details are loaded from the database.
7. Spring Security stores the authenticated user in SecurityContextHolder.
8. Protected APIs become accessible.

---

## Security

Current Status:

* BCrypt Password Encryption Implemented
* JWT Token Generation Implemented
* Spring Security Configured
* JWT Token Validation Implemented
* Protected APIs Implemented
* Stateless Authentication
* Protected APIs using Spring Security

---

## Project Structure

src/main/java/com/quickbite

controller/
service/
repository/
entity/
dto/
config/
security/
filter/
util/
exception/

---

## Progress Tracker

### Phase 1 - User Module

* [x] Spring Boot Setup
* [x] PostgreSQL Configuration
* [x] User Entity
* [x] User Repository
* [x] Registration API
* [x] BCrypt Password Encryption
* [x] Login API
* [x] JWT Token Generation
* [x] JWT Request Validation
* [x] Protected APIs

### Phase 2 - Restaurant Module

* [x] Restaurant Entity
* [x] Restaurant APIs
* [x] Global Exception Handling
* [x] Food Item Entity
* [x] Food Item APIs

### Phase 3 - Order Module

* [x] Order Entity
* [x] Order APIs
* [ ] Order Tracking

### Phase 4 - Event Driven Architecture

* [ ] Kafka Setup
* [ ] Order Events
* [ ] Notification Service

### Phase 5 - Deployment

* [ ] Docker
* [ ] Docker Compose
* [ ] GitHub Documentation

---

## Learning Outcomes

* Spring Boot REST APIs
* Layered Architecture
* Spring Security
* JWT Authentication
* Database Design
* Microservices
* Request Validation using Bean Validation
* Global Exception Handling
* REST API Error Handling
* JPA Entity Relationships
* One-To-Many Mapping
* Many-To-One Mapping
* Foreign Keys
* Event-Driven Systems
* Kafka Messaging
* Containerization using Docker
* Event-Driven Architecture *(Upcoming)*
* Redis Caching *(Upcoming)*
* Kafka Messaging *(Upcoming)*
* Docker Containerization *(Upcoming)*
