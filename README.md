🏢 Employee Dashboard Backend
A comprehensive employee management system built with Spring Boot, PostgreSQL, and Angular integration.
📋 Table of Contents

Overview
Features
Tech Stack
Quick Start
API Documentation
Database Setup
Configuration
Development
Testing
Deployment
Contributing

🎯 Overview
The Employee Dashboard Backend provides a robust API for managing employees, administrators, and organizational data. It features role-based access control, secure authentication, and comprehensive employee management capabilities.
Key Capabilities

🔐 Secure authentication system (no Spring Security dependency)
👥 Multi-role user management (Admin, HR, Manager, Developer, DevOps)
📊 Employee CRUD operations with advanced filtering
🔒 Role-based permissions and entitlements
📈 Performance optimized with connection pooling
🛠️ Enterprise-grade logging and error handling

✨ Features
Authentication & Security

 Simple username/password authentication
 Session token management
 Role-based access control
 User session validation
 Password encryption (planned)
 Multi-factor authentication (planned)

Employee Management

 Admin user management
 Employee CRUD operations (in progress)
 Department management
 Role assignment
 Bulk employee import
 Employee search and filtering

API Features

 RESTful API design
 Consistent response format
 Comprehensive error handling
 Request/response logging
 API versioning (planned)
 Rate limiting (planned)

🛠️ Tech Stack
Backend

Framework: Spring Boot 3.5.0
Language: Java 17
Database: PostgreSQL
ORM: Spring Data JDBC
Migration: Liquibase
Build Tool: Gradle (Kotlin DSL)
Logging: SLF4J + Logback

Database

Primary DB: PostgreSQL
Connection Pool: HikariCP
Schema Management: Liquibase migrations

Development Tools

IDE: IntelliJ IDEA (recommended)
API Testing: Postman
Version Control: Git

🚀 Quick Start
Prerequisites

Java 17 or higher
PostgreSQL 12 or higher
Gradle 8.5 or higher (or use wrapper)

1. Clone the Repository
bashgit clone https://github.com/yourusername/employee-dashboard-backend.git
cd employee-dashboard-backend
2. Database Setup
sql-- Create database
CREATE DATABASE employee_dashboard_db;

-- Create user (optional)
CREATE USER app_user WITH PASSWORD '12345';
GRANT ALL PRIVILEGES ON DATABASE employee_dashboard_db TO app_user;
3. Configuration
Update src/main/resources/application.properties:
properties# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_dashboard_db
spring.datasource.username=user
spring.datasource.password=12345
4. Run the Application
bash# Using Gradle wrapper
./gradlew bootRun

# Or using your installed Gradle
gradle bootRun
5. Verify Installation
bash# Health check
curl http://localhost:8080/api/admin/getCurrentAdmin

# Expected response: Admin user data or 404 if no admin exists
📚 API Documentation
Base URL
http://localhost:8080/api
Authentication Endpoints
Login
httpPOST /api/auth/login
Content-Type: application/json

{
    "username": "yx1214@gmail.com",
    "password": "ck"
}
Response:
json{
    "success": true,
    "message": "Login successful",
    "sessionToken": "abc123-def456-789xyz",
    "userInfo": {
        "id": 1,
        "username": "yx1214@gmail.com",
        "email": "yx1214@gmail.com",
        "firstName": "ck",
        "lastName": "yp",
        "role": "ADMIN",
        "active": true
    }
}
Get Current User
httpGET /api/auth/current-user
Authorization: Bearer {sessionToken}
Logout
httpPOST /api/auth/logout
Authorization: Bearer {sessionToken}
Admin Endpoints
Get Current Admin
httpGET /api/admin/getCurrentAdmin
Response Format
All API responses follow this consistent format:
json{
    "success": boolean,
    "message": "string",
    "data": object | null
}
🗄️ Database Setup
Automatic Setup
The application uses Liquibase for automatic database schema management. On first run, it will:

Create all necessary tables
Set up indexes and constraints
Insert sample data (if configured)

Manual Setup
If you prefer manual setup:
sql-- See src/main/resources/employee-dashboard-db/liquibase/changelog/
-- for complete schema definitions
Sample Data
sql-- Sample admin user for testing
INSERT INTO admin (username, password, email, "firstName", "lastName", active, role, entitlements)
VALUES ('yx1214@gmail.com', 'ck', 'yx1214@gmail.com', 'ck', 'yp', true, 'ADMIN', 'ADMIN');
⚙️ Configuration
Application Properties
properties# Server configuration
server.port=8080

# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_dashboard_db
spring.datasource.username=user
spring.datasource.password=12345
spring.datasource.driver-class-name=org.postgresql.Driver

# Connection pool settings
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5

# Liquibase configuration
spring.liquibase.change-log=classpath:employee-dashboard-db/liquibase/changelog/db.changelog-master.xml
spring.liquibase.enabled=true

# Logging configuration
logging.level.employee_management=DEBUG
logging.level.org.springframework.web=DEBUG
Environment Variables
For production deployment:
bashexport DB_URL=jdbc:postgresql://prod-db:5432/employee_dashboard_db
export DB_USERNAME=prod_user
export DB_PASSWORD=secure_password
export SERVER_PORT=8080
🧪 Development
Project Structure
src/main/java/employee_management/
├── controller/          # REST controllers
├── service/            # Business logic
├── dao/               # Data access layer
├── dto/               # Data transfer objects
│   └── auth/          # Authentication DTOs
├── domain/            # Domain models
│   ├── model/         # Entity classes
│   └── enums/         # Business enums
└── config/            # Configuration classes

src/main/resources/
├── application.properties
└── employee-dashboard-db/
    └── liquibase/
        └── changelog/  # Database migrations
Code Style

Use Java 17 features
Follow Spring Boot conventions
Use Lombok for reducing boilerplate
Implement comprehensive logging
Write meaningful commit messages

Adding New Features

Create feature branch: git checkout -b feature/employee-crud
Add Liquibase migration if needed
Implement DAO, Service, Controller layers
Add comprehensive logging
Test with Postman
Update README if needed

🧪 Testing
Manual Testing with Postman
Import the Postman collection: docs/Employee-Dashboard-API.postman_collection.json
Test Scenarios

Authentication Flow:

Login with valid credentials
Access protected endpoints
Logout and verify session invalidation


Error Handling:

Invalid credentials
Expired sessions
Malformed requests



Database Testing
sql-- Verify admin table
SELECT * FROM admin;

-- Check database connections
SELECT count(*) FROM admin WHERE active = true;
🚀 Deployment
Local Development
bash./gradlew bootRun
Production Build
bash# Build JAR
./gradlew build

# Run JAR
java -jar build/libs/employee-management-0.0.1-SNAPSHOT.jar
Docker (Planned)
dockerfile# Coming soon - Dockerfile and docker-compose.yml
Environment Setup

Development: H2/PostgreSQL local
Testing: PostgreSQL test instance
Production: PostgreSQL with connection pooling

📈 Performance
Database Optimization

Connection pooling with HikariCP
Proper indexing on frequently queried columns
Liquibase for schema versioning

Monitoring

Comprehensive logging with SLF4J
Database connection pool monitoring
Request/response logging for debugging

🤝 Contributing
Development Workflow

Fork the repository
Create feature branch: git checkout -b feature/amazing-feature
Commit changes: git commit -m 'Add amazing feature'
Push to branch: git push origin feature/amazing-feature
Open a Pull Request

Coding Standards

Use meaningful variable names
Add JavaDoc for public methods
Follow RESTful API conventions
Implement proper error handling
Add comprehensive logging

📞 Support
Common Issues
Issue: Application won't start
bash# Check Java version
java --version

# Check PostgreSQL connection
psql -h localhost -U user -d employee_dashboard_db
Issue: Database connection errors
bash# Verify PostgreSQL is running
sudo systemctl status postgresql

# Check database exists
psql -l | grep employee_dashboard
Getting Help

Check the logs: logs/employee-management.log
Review application.properties configuration
Verify database connectivity
Check Postman collection for API examples

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.
🙏 Acknowledgments

Spring Boot community for excellent documentation
PostgreSQL team for robust database engine
Liquibase for database migration management


📊 Project Status

✅ Authentication System - Complete
✅ Admin Management - Complete
🚧 Employee CRUD - In Progress
📋 Role Management - Planned
📋 Department Management - Planned
📋 Reporting System - Planned

Current Version: 0.1.0-SNAPSHOT
Last Updated: May 31, 2025

For more information, contact the development team or create an issue in the repository.
