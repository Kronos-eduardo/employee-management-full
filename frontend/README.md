# Employee Management Frontend

A modern, responsive Angular 21 frontend application for managing employees with JWT authentication and complete CRUD operations.

## Features

- Angular 21 with standalone components
- JWT-based authentication
- HTTP interceptor for automatic token injection
- Route guards for protected routes
- Responsive design
- Real-time employee management CRUD
- Employee search functionality
- Input validation
- Error handling

## Prerequisites

- Node.js 20.x or higher
- npm 10.x or higher

## Installation and Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd employee-frontend
```

### 2. Install Dependencies

```bash
npm install
```

## Running the Application

### Development Server

```bash
npm start
```

Navigate to `http://localhost:4200/`. The application will automatically reload if you change any source files.

### Build for Production

```bash
npm run build
```

The build artifacts will be stored in the `dist/` directory.

## Default Credentials

**Username:** admin
**Password:** admin123

## API Configuration

The API URL is configured in the services:
- Backend URL: `http://localhost:8080`
- Update service files if backend is on different host

## Routes

- `/login` - Public login page
- `/employees` - Employee list (protected)
- `/employees/create` - Create employee (protected)
- `/employees/:id/edit` - Edit employee (protected)

## Project Structure

```
employee-frontend/
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   ├── login/
│   │   │   ├── employee-list/
│   │   │   └── employee-form/
│   │   ├── services/
│   │   │   ├── auth.service.ts
│   │   │   └── employee.service.ts
│   │   ├── interceptors/
│   │   │   └── jwt.interceptor.ts
│   │   ├── guards/
│   │   │   └── auth.guard.ts
│   │   ├── app.ts
│   │   ├── app.routes.ts
│   │   └── app.config.ts
│   └── styles.css
├── package.json
├── Dockerfile
└── README.md
```

## Authentication

1. User logs in with credentials
2. Backend returns JWT token
3. Token stored in localStorage
4. Interceptor automatically adds token to requests
5. Route guards prevent unauthorized access

## Services

### AuthService
- `login(credentials)` - Authenticate and get token
- `logout()` - Clear token
- `isAuthenticated()` - Check auth status

### EmployeeService
- `getAllEmployees()` - Get all employees
- `getEmployeeById(id)` - Get single employee
- `searchByName(name)` - Search by name
- `createEmployee(employee)` - Create employee
- `updateEmployee(id, employee)` - Update employee
- `deleteEmployee(id)` - Delete employee

## Docker Deployment

### Build Image

```bash
docker build -t employee-frontend:latest .
```

### Run with Docker Compose

```bash
docker-compose up -d
```

Frontend runs on `http://localhost:4200`

## Troubleshooting

### CORS Errors
- Ensure backend is running on `http://localhost:8080`
- Check CORS configuration in backend

### Login Fails
- Verify backend is accessible
- Check credentials (admin/admin123)
- Review browser console for errors

## Technology Stack

- Angular 21
- TypeScript
- RxJS
- Standalone Components
- HTTP Interceptors

## License

MIT License
