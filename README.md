# Domain

## About
The Domain Library contains core domain models and repository definitions 
using ORM. It also provides shared framework-level dependencies and 
abstractions for Spring-based services, ensuring consistency and reuse 
across the system's backend components.

## System Requirements

- Java 21
- Apache Maven 3.9.9
- Docker (if running the service within the Docker container)

## Configuration

### Database
Configure database connection in `application.yaml` file:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/evidentor
    driverClassName: org.postgresql.Driver
    username: postgres
    password: postgres
```

## How to Install?

### 1. Clone the repository
```shell
git clone https://github.com/Evidentor/Domain.git
cd Domain
```

### 2. Install dependencies
```shell
mvn clean install
```

## How to Test?
```shell
mvn test
```
