# 🚀 HireHub – Job Portal Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Backend-green)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange)
![JWT](https://img.shields.io/badge/Auth-JWT-red)
![Status](https://img.shields.io/badge/Project-80%25_Complete-yellow)

---

## 📖 Overview

**HireHub** is a backend-driven job portal application designed to connect **job seekers** and **recruiters** efficiently.

It provides a secure and scalable system where recruiters can manage job postings and applicants, while job seekers can explore and apply for opportunities seamlessly.

🎯 **Goal:**
To build a **production-ready, role-based job portal backend** using modern technologies and best practices.

---

## ✨ Features

### 🔐 Authentication & Security

* JWT-based authentication
* Secure login & registration
* Role-based authorization (Recruiter / Job Seeker)

### 👨‍💼 Recruiter Features

* Post new jobs
* Update existing jobs
* Delete jobs
* View applicants

### 👩‍💻 Job Seeker Features

* Browse available jobs
* Apply for jobs

### 📄 Application System

* Apply to jobs
* Track applications
* Recruiters can view all applicants

---

## 🛠️ Tech Stack

| Category   | Technology            |
| ---------- | --------------------- |
| Language   | Java 17               |
| Framework  | Spring Boot           |
| Security   | Spring Security + JWT |
| Database   | MySQL                 |
| Build Tool | Maven                 |

---

## 📂 Project Structure

```
hirehub-backend/
│
├── controller/      # REST APIs
├── service/         # Business logic
├── repository/      # Database layer
├── entity/          # JPA entities
├── config/          # Security configuration
└── dto/             # Data transfer objects (optional)
```

---

## ⚙️ Setup & Installation

### 🔹 Prerequisites

* Java 17+
* Maven
* MySQL

---

### 🔹 Steps to Run

1️⃣ Clone the repository:
```bash
git clone https://github.com/BharathVeeraboina/hirehub.git
```

2️⃣ Navigate into the project:
```bash
cd hirehub
```

3️⃣ Configure MySQL in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hirehub
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4️⃣ Run the application:
```bash
mvn spring-boot:run
```

5️⃣ Server starts at:
👉 http://localhost:8080

---

## 🔐 Authentication

This project uses **JWT (JSON Web Token)** for secure access.

📌 Include token in request headers:
```
Authorization: Bearer <your_token>
```

---

## 📡 API Endpoints

### 🔑 Authentication APIs

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| POST   | /api/auth/register | Register new user |
| POST   | /api/auth/login    | Login user        |

---

### 💼 Job APIs

| Method | Endpoint       | Description                 |
| ------ | -------------- | --------------------------- |
| GET    | /api/jobs      | Get all jobs                |
| POST   | /api/jobs      | Create job (Recruiter only) |
| PUT    | /api/jobs/{id} | Update job                  |
| DELETE | /api/jobs/{id} | Delete job                  |

---

### 📄 Application APIs

| Method | Endpoint                        | Description       |
| ------ | ------------------------------- | ----------------- |
| POST   | /api/applications/apply/{jobId} | Apply for job     |
| GET    | /api/applications               | View applications |

---

## 📊 Project Status

🚧 **Backend: 80% Complete**

### ✅ Completed

* Authentication & JWT security
* Role-based access control
* Job CRUD operations
* Job application system
* Application tracking

### 🔄 Pending

* Frontend (React)
* UI integration
* Advanced search & filters
* Notifications system

---

## 🚀 Future Enhancements

* 🌐 React Frontend
* 📧 Email notifications
* 📊 Admin dashboard
* 🔍 Advanced filtering
* 📁 Resume upload

---

## 📸 API Testing (Optional)

You can test APIs using:

* Postman
* Swagger (can be added later)

---

## 👨‍💻 Author

**Veeraboina Bharath**

🔗 GitHub: https://github.com/BharathVeeraboina

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push and open a Pull Request

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!

---

## 📜 License

This project is licensed under the MIT License.
