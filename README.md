# 🚌 Public Transport Management System – Backend

A Spring Boot based RESTful backend application for managing public transportation services including trips, routes, vehicles, seats, and ticket booking. Supports **role-based access control**, **JWT authentication**, and **search functionality for trips**.

---

## 🚀 Tech Stack

| Technology | Usage |
|-----------|--------|
| **Java 17** | Core language |
| **Spring Boot 3** | Backend framework |
| **Spring Security + JWT** | Authentication & Authorization |
| **Spring Data JPA / Hibernate** | ORM & Repository layer |
| **MySQL / PostgreSQL** | Database |
| **Lombok** | Reduce boilerplate |
| **React / React Query (Frontend)** | Client application (separate repo) |

---

## 🔐 Authentication & Roles

System uses JWT tokens for security and supports three major roles:

| Role | Capabilities |
|-------|--------------|
| **ADMIN** | Manage trips, vehicles, routes & users |
| **DRIVER** | Access assigned trips |
| **USER** | Search trips, book seats & manage tickets |

Permissions mapped via RolePermissionMapping class and enforced using **@PreAuthorize** annotations.

---

## 📦 Main Features

Register and Login with JWT
Create & Manage Routes, RouteStops, Trips & Vehicles
Dynamic Seats Management for each trip
Trip search by **source, destination & date**
Ticket booking system
Role-based access and UI access control
