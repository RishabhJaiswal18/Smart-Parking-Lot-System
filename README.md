# Smart-Parking-Lot-System
Demo for Smart Parking Lot System 🚗

## 📌 Project Overview
The Smart Parking Lot System is a backend application built using **Java 17, Spring Boot, and MySQL**.  
It simulates the real-world functioning of a parking lot by handling vehicle entry, parking spot allocation, exit processing, and fee calculation.

The system automatically assigns parking spots based on vehicle type, tracks parking duration, and calculates parking fees upon exit.

---

## 🎯 Features
- Vehicle Check-in and Check-out
- Automatic Parking Spot Allocation
- Parking Fee Calculation based on duration and vehicle type
- Real-time Parking Spot Availability
- Concurrency-safe spot allocation using database locking

---

## 🛠️ Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- REST APIs

---

## 🧠 System Design (Layman Explanation)

### Entry Flow
1. Vehicle enters the parking lot
2. System checks for an available parking spot based on vehicle type
3. Entry time is recorded
4. Parking ticket is generated

### Exit Flow
1. Vehicle provides ticket ID
2. System calculates parking duration
3. Fee is calculated
4. Parking spot is marked as available

---

## 🗂️ Database Design

### Parking Spot Table
- Stores parking spots with type and availability

### Parking Ticket Table
- Stores vehicle details, entry/exit time, and fee

---

## 🔒 Concurrency Handling
- Uses **PESSIMISTIC_WRITE** locking to prevent multiple vehicles from getting the same parking spot
- Uses `@Transactional` to ensure atomic operations

## ▶️ How to Run the Project

1. Clone the repository
2. Create MySQL database `parking_db`
3. Update DB credentials in `application.properties`
4. Insert initial parking spots into `parking_spot` table
5. Run the Spring Boot application

---

## 🧪 Sample SQL for Parking Spots
```sql
INSERT INTO parking_spot (spot_type, occupied) VALUES
('SMALL', false),
('MEDIUM', false),
('MEDIUM', false),
('LARGE', false);
