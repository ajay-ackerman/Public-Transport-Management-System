# 🚍 Public Transport Management System – Backend

This is the **backend service** for the **Public Transport Management System**, responsible for handling authentication, trip management, seat allocation, and ticket booking.

The backend is built using **Spring Boot** and follows a clean, layered architecture with secure role-based access control.

### 🔗FrontEnd Repo
https://github.com/ajay-ackerman/Public-Transport-Management-System-FrontEnd/
---

## ⚙️ Features

### 🔐 Authentication & Security
- JWT-based authentication
- Role-based authorization (ADMIN, DRIVER, PASSENGER)
- Refresh token service
- Secure login & signup APIs

### 🚍 Trip Management
- Create and manage Routes and trips (scheduled & ad-hoc)
- Assign vehicles and drivers to trips
- Start and end trips based on status
- Search trips by **source, destination, and date**

### 💺 Seat Management
- Initialize seats per trip
- Fetch all seats or available seats
- Prevent double booking using DB constraints
- Seat status tracking (AVAILABLE / BOOKED)

### 🎟️ Ticket Booking
- Book tickets based on selected seat(s)
- Maintain booking history per passenger
- Ticket lifecycle management (BOOKED / CANCELLED / USED)

### 📊 User Management
- Admin, Driver, and Passenger roles
- Driver-specific trip visibility
- Passenger booking history (My Trips)

---

## 🛠 Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot
- **Security:** Spring Security + JWT
- **ORM:** Spring Data JPA (Hibernate)
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Containerization:** Docker
- **Deployment:** Render

---
