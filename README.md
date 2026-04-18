# Pharmacy Ordering Website

## Description

This is a full-stack pharmacy ordering backend application built with Spring Boot. It provides RESTful APIs for managing pharmacy operations including user authentication, medicine inventory, order processing, and prescription management.

## Features

- User authentication and authorization with JWT
- Medicine and inventory management
- Order processing and tracking
- Prescription management
- Category-based medicine organization
- Email notifications
- Rate limiting and security configurations
- Swagger API documentation

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd pharmacy-ordering-website
   ```

2. Ensure Java 17 is installed:
   ```bash
   java -version
   ```

3. Ensure Maven is installed:
   ```bash
   mvn -version
   ```

## Configuration

1. Set up MySQL database:
   - Create a database named `pharmacy_db`
   - Update `src/main/resources/application.properties` with your MySQL credentials:
     ```
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

2. Configure email settings (optional, for notifications):
   - Set `MAIL_PASSWORD` environment variable or update `application.properties`:
     ```
     spring.mail.username=your_email@gmail.com
     spring.mail.password=your_app_password
     ```

## Running the Application

1. Build the project:
   ```bash
   ./mvnw clean compile
   ```

2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

   Or using Maven wrapper:
   ```bash
   mvnw.cmd spring-boot:run
   ```

3. The application will start on `http://localhost:8080`

## API Documentation

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/api-docs`

### Main API Endpoints

- **Authentication**: `/api/auth`
  - POST `/register` - User registration
  - POST `/login` - User login

- **Medicines**: `/api/medicines`
- **Categories**: `/api/categories`
- **Inventory**: `/api/inventory`
- **Orders**: `/api/orders`
- **Prescriptions**: `/api/prescriptions`

## Database Setup

The application uses MySQL and will automatically create tables on startup due to `spring.jpa.hibernate.ddl-auto=update`. Initial data can be loaded from `src/main/resources/data.sql`.

## Testing

Run tests with:
```bash
./mvnw test
```

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running on port 3306
- Verify database credentials in `application.properties`
- Check if `pharmacy_db` database exists or can be created

### Port Conflicts
- If port 8080 is in use, change it in `application.properties`:
  ```
  server.port=8081
  ```

### Build Failures
- Ensure Java 17 is set as the default JDK
- Clear Maven cache: `mvn clean`
- Check for dependency conflicts

### Email Notifications Not Working
- Ensure Gmail app password is set correctly
- Check firewall settings for SMTP port 587

## Project Structure

```
src/
├── main/
│   ├── java/com/pharmacy/
│   │   ├── PharmacyApplication.java
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── dto/             # Data transfer objects
│   │   ├── entity/          # JPA entities
│   │   ├── enums/           # Enums
│   │   ├── exception/       # Exception handlers
│   │   ├── repository/      # JPA repositories
│   │   ├── security/        # Security configurations
│   │   └── service/         # Business logic services
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/                    # Unit and integration tests
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.
