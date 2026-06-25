# QuickBite - Event-Driven Food Delivery Backend

## Overview

QuickBite is a backend food delivery application built using Spring Boot and PostgreSQL.

The project is being developed incrementally, starting with a monolithic architecture and later evolving into a microservices-based, event-driven system using Kafka and Docker.

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
* Kafka
* Docker

---

## Current Features

### User Management

* User Registration
* User Login
* JWT Token Generation
* Password Encryption using BCrypt
* Role-Based User Model

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

## Architecture Implemented

**Controller Layer** <br>
        ↓ <br>
**Service Layer** <br>
        ↓ <br>
**Repository Layer** <br>
        ↓ <br>
**PostgreSQL Database** <br>

---

## Security

Current Status:

* BCrypt Password Encryption Implemented
* JWT Authentication Pending
* Spring Security Configured
* JWT Validation Pending

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
* [ ] JWT Request Validation
* [ ] Protected APIs

### Phase 2 - Restaurant Module

* [ ] Restaurant Entity
* [ ] Food Item Entity
* [ ] Restaurant APIs

### Phase 3 - Order Module

* [ ] Order Entity
* [ ] Order APIs
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
* Event-Driven Systems
* Kafka Messaging
* Containerization using Docker
