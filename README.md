# Employee Management API

A robust, secure, and scalable REST API for managing employee operations built with Spring Boot 2.7, implementing hexagonal architecture, Spring Security, JWT authentication, and CORS configuration.

## Features

- REST API with CRUD operations for employee management
- Hexagonal (Clean) Architecture
- JWT-based authentication and authorization
- Spring Security configuration
- CORS support
- OpenAPI/Swagger documentation
- Comprehensive unit tests with JUnit 5 and Mockito
- H2 in-memory database
- Health check with Spring Boot Actuator
- Structured logging
- Docker support

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose (for containerization)

## Installation and Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd employee-api
```

### 2. Build the Project

```bash
./mvnw clean package
```

Or on Windows:

```bash
mvnw.cmd clean package
```

### 3. Run the Application

```bash
java -jar target/employee-api-0.0.1-SNAPSHOT.jar
```

The API will be available at `http://localhost:8080`

## Default Credentials

**Username:** admin
**Password:** admin123

## API Endpoints

### Authentication

- **POST** `/auth/login` - Login and get JWT token
  ```json
  {
    "username": "admin",
    "password": "admin123"
  }
  ```

### Employee Management

- **GET** `/api/employees` - Get all employees (Requires JWT)
- **GET** `/api/employees/{id}` - Get employee by ID (Requires JWT)
- **GET** `/api/employees/search?name={name}` - Search employees by name (Requires JWT)
- **POST** `/api/employees` - Create one or multiple employees (Requires JWT)
  ```json
  [
    {
      "firstName": "John",
      "middleName": "Michael",
      "paternalLastName": "Doe",
      "maternalLastName": "Smith",
      "age": 30,
      "gender": "Male",
      "birthDate": "01-01-1994",
      "jobTitle": "Software Engineer",
      "active": true
    }
  ]
  ```
- **PUT** `/api/employees/{id}` - Update employee (Requires JWT)
- **DELETE** `/api/employees/{id}` - Delete employee (Requires JWT)

### Documentation

- **Swagger UI** - `http://localhost:8080/swagger-ui.html`
- **OpenAPI Docs** - `http://localhost:8080/v3/api-docs`

### Health Check

- **GET** `/actuator/health` - Application health status

## JWT Token Usage

When making requests to protected endpoints, include the JWT token in the Authorization header:

```
Authorization: Bearer <token>
```

Example:
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  http://localhost:8080/api/employees
```

## Database

The application uses H2 in-memory database. Access the H2 console at:

```
http://localhost:8080/h2-console
```

**H2 Console Credentials:**
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## Project Structure

```
employee-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/employeeapi/
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── Employee.java
│   │   │   │   │   └── User.java
│   │   │   │   └── repository/
│   │   │   │       └── EmployeeRepositoryPort.java
│   │   │   ├── application/
│   │   │   │   └── service/
│   │   │   │       └── EmployeeService.java
│   │   │   ├── infrastructure/
│   │   │   │   ├── adapter/
│   │   │   │   │   ├── in/
│   │   │   │   │   │   ├── web/
│   │   │   │   │   │   │   ├── EmployeeController.java
│   │   │   │   │   │   │   └── AuthController.java
│   │   │   │   │   │   └── dto/
│   │   │   │   │   │       ├── EmployeeRequest.java
│   │   │   │   │   │       ├── LoginRequest.java
│   │   │   │   │   │       └── AuthResponse.java
│   │   │   │   │   └── out/
│   │   │   │   │       └── persistence/
│   │   │   │   │           ├── EmployeeEntity.java
│   │   │   │   │           ├── EmployeePersistenceAdapter.java
│   │   │   │   │           └── SpringDataEmployeeRepository.java
│   │   │   │   └── security/
│   │   │   │       ├── SecurityConfig.java
│   │   │   │       ├── JwtService.java
│   │   │   │       └── JwtAuthenticationFilter.java
│   │   │   └── EmployeeApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/employeeapi/
│           ├── application/service/
│           │   └── EmployeeServiceTest.java
│           └── infrastructure/security/
│               └── JwtServiceTest.java
├── pom.xml
├── Dockerfile
└── docker-compose.yml
```

## Running Tests

```bash
./mvnw clean test
```

Or on Windows:

```bash
mvnw.cmd clean test
```

## Docker Deployment

### Build Docker Image

```bash
./mvnw clean package
docker build -t employee-api:latest .
```

### Run with Docker Compose

```bash
docker-compose up -d
```

This will start:
- Backend API on `http://localhost:8080`
- Frontend on `http://localhost:4200`

## Environment Variables

For production use, configure the following environment variables:

- `APP_JWT_SECRET` - JWT secret key for token signing
- `SPRING_DATASOURCE_URL` - Database connection URL
- `SPRING_DATASOURCE_USERNAME` - Database username
- `SPRING_DATASOURCE_PASSWORD` - Database password

## Security

- All employee endpoints require JWT authentication
- CORS is configured to allow requests from `http://localhost:4200` (Angular frontend), `http://localhost:3000`, and `http://localhost:8080`
- Passwords should be securely hashed in production
- Use HTTPS in production environments
- Regularly rotate JWT secrets

## Logging

Application logs are configured in `application.properties`:

- Root log level: INFO
- Application log level: DEBUG
- Console pattern: `%d{yyyy-MM-dd HH:mm:ss} - %logger{36} - %msg%n`

## Technology Stack

- **Framework:** Spring Boot 2.7.18
- **Language:** Java 17
- **Security:** Spring Security + JWT (JJWT)
- **Database:** H2 (Development)
- **ORM:** Spring Data JPA / Hibernate
- **Validation:** Jakarta Validation
- **Documentation:** Springdoc OpenAPI (Swagger)
- **Testing:** JUnit 5, Mockito
- **Build Tool:** Maven
- **Containerization:** Docker

## Troubleshooting

### JWT Token Expired

The token expires after 1 hour. Re-login to get a new token:

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

### CORS Errors

Ensure the frontend URL is included in the `corsConfigurationSource()` method in `SecurityConfig.java`.

### Database Connection Issues

Verify H2 console is accessible at `http://localhost:8080/h2-console` with credentials mentioned above.

## License

This project is licensed under the MIT License.

## Support

For issues or questions, please create an issue in the repository or contact the development team.

