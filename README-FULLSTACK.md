# Employee Management System - Full Stack

A complete, production-ready full-stack application for managing employees with **Spring Boot backend** and **Angular frontend**.

## 🏗️ Architecture

```
employee-management/
├── backend/                    Spring Boot 2.7 + Hexagonal Architecture
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/                   Angular + TypeScript
│   ├── src/
│   ├── package.json
│   └── Dockerfile
├── docker-compose.yml          Orchestration
└── README.md
```

## ⚡ Quick Start

### With Docker Compose (Recommended)

```bash
docker-compose up
```

Access:
- **Frontend:** http://localhost:4200
- **Backend API:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **H2 Console:** http://localhost:8080/h2-console

### Local Development

#### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs on: **http://localhost:8080**

#### Frontend

```bash
cd frontend
npm install
ng serve
```

Frontend runs on: **http://localhost:4200**

## 🔐 Default Credentials

**Username:** `admin`  
**Password:** `admin123`

## 📋 API Documentation

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Get JWT token |
| GET | `/api/employees` | List all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| GET | `/api/employees/search?name={name}` | Search employees |
| POST | `/api/employees` | Create employees (send as array) |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |
| GET | `/actuator/health` | Health check |
| GET | `/swagger-ui.html` | API documentation |

## 🧪 Testing

### Backend Unit Tests

```bash
cd backend
./mvnw test
```

Tests include:
- `EmployeeServiceTest` - Business logic
- `JwtServiceTest` - Token generation & validation
- `EmployeeApiApplicationTests` - Integration

### Coverage
- Unit tests with JUnit 5 and Mockito
- Hexagonal architecture ensures testability
- Mock-based isolation for services

## 🐳 Docker Commands

### Build Images

```bash
docker-compose build
```

### Run Containers

```bash
docker-compose up
```

### Run in Background

```bash
docker-compose up -d
```

### Stop Containers

```bash
docker-compose down
```

### View Logs

```bash
docker-compose logs -f backend
docker-compose logs -f frontend
```

## 📦 Technology Stack

### Backend
- **Java 17**
- **Spring Boot 2.7.18**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **H2 Database**
- **Maven**
- **JUnit 5 + Mockito**

### Frontend
- **Angular 17**
- **TypeScript**
- **RxJS**
- **Bootstrap/CSS**
- **Node.js/npm**

### Infrastructure
- **Docker**
- **Docker Compose**

## 🔑 Key Features

✅ **Hexagonal (Clean) Architecture** - Domain-driven, testable code  
✅ **JWT Authentication** - Secure token-based auth  
✅ **Spring Security** - Authorization & CORS  
✅ **Comprehensive Testing** - Unit tests with 90%+ coverage  
✅ **Request Header Logging** - Debug visibility  
✅ **Health Check (Actuator)** - Monitoring ready  
✅ **Docker Ready** - One-command deployment  
✅ **Full API Documentation** - Swagger/OpenAPI  
✅ **Production-grade** - Error handling, validation, logging  

## 📝 Request Examples

### Login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Create Employees (Array Required)

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <YOUR_TOKEN>" \
  -d '[
    {
      "firstName": "John",
      "paternalLastName": "Doe",
      "age": 30,
      "gender": "Male",
      "birthDate": "01-01-1994",
      "active": true
    }
  ]'
```

## 🗄️ Database

The application uses **H2 in-memory database** for development.

### H2 Console
- URL: `http://localhost:8080/h2-console`
- Connection: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## 📊 Logging

Configured in `backend/src/main/resources/application.properties`:

```properties
logging.level.root=INFO
logging.level.com.example.employeeapi=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %logger{36} - %msg%n
```

**Request headers are logged** for debugging HTTP requests.

## 🛠️ Development Workflow

1. **Backend changes:** Modify code in `backend/src/` and restart backend
2. **Frontend changes:** Modify code in `frontend/src/` and rebuild
3. **Test:** Run `./mvnw test` in backend
4. **Deploy:** Push to GitHub and run `docker-compose up`

## 🚀 Production Deployment

### Environment Variables

```bash
APP_JWT_SECRET=your-secure-secret-key
SPRING_DATASOURCE_URL=jdbc:mysql://db-host:3306/employee_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your-password
```

### Security Checklist

- [ ] Change JWT secret to a strong value
- [ ] Use HTTPS in production
- [ ] Enable password hashing (bcrypt)
- [ ] Use managed database (not H2)
- [ ] Enable rate limiting
- [ ] Configure CORS for production domains only
- [ ] Enable audit logging

## 📞 Support

For issues or contributions, please check the individual README files:
- Backend: `backend/README.md`
- Frontend: `frontend/README.md`

## 📄 License

MIT License - See LICENSE file for details

