# SME Business Operating System (SME-BOS)

**A secure, modular business management platform designed to help small and medium-sized enterprises manage their day-to-day operations.**

Built with Java 21, Spring Boot, PostgreSQL, and JWT-based authentication.

---

## Overview

Small and medium-sized enterprises often manage sales, customers, inventory, and expenses across disconnected tools and spreadsheets. This makes it difficult to maintain accurate records, monitor business performance, and control access to sensitive information.

The SME Business Operating System (SME-BOS) is a backend application designed to bring essential business operations into one system.

The project demonstrates backend engineering, relational database design, authentication, authorization, API development, and the foundations of a cloud-ready application.

## Project Objectives

* Provide a structured foundation for managing business operations.
* Implement secure authentication using JSON Web Tokens (JWT).
* Enforce role-based access control (RBAC).
* Maintain relational data using PostgreSQL.
* Manage database changes using Flyway migrations.
* Provide REST APIs for business functionality.
* Support health monitoring through Spring Boot Actuator.
* Establish a foundation for automated testing, CI/CD, and cloud deployment.

## Technology Stack

| Technology           | Purpose                            |
| -------------------- | ---------------------------------- |
| Java 21              | Backend programming language       |
| Spring Boot 3.5.6    | Application framework              |
| Spring Web           | REST API development               |
| Spring Security      | Authentication and authorization   |
| JWT (JJWT 0.12.6)    | Token-based authentication         |
| Spring Data JPA      | Database access and ORM            |
| PostgreSQL 16        | Relational database                |
| Flyway               | Database schema migrations         |
| Maven                | Dependency management and builds   |
| Spring Boot Actuator | Application health and monitoring  |
| Git and GitHub       | Version control and source hosting |
| Ubuntu on WSL2       | Development environment            |

## Architecture

The application follows a layered backend architecture.

```text
                    Client / API Consumer
                             |
                             v
                     REST Controllers
                             |
                             v
                   Spring Security Filter
                             |
                             v
                    Authentication / JWT
                             |
                             v
                     Business Services
                             |
                             v
                    Spring Data JPA
                             |
                             v
                        PostgreSQL
                             |
                             ^
                             |
                    Flyway Migrations
```

The security layer validates authentication and applies authorization rules before protected endpoints are accessed.

The persistence layer uses JPA repositories to interact with PostgreSQL, while Flyway maintains version-controlled database migrations.

## Current Features

### 1. Authentication and Security

* Login API.
* JWT-based authentication.
* Environment-based JWT secret configuration.
* Spring Security integration.
* Role-based access control.

### 2. Role-Based Access Control

The application defines three user roles:

| Role      | Intended access                                      |
| --------- | ---------------------------------------------------- |
| `OWNER`   | Administrative functionality and owner-level reports |
| `MANAGER` | Authorized reporting and operational functionality   |
| `STAFF`   | Restricted access based on assigned permissions      |

Authorization rules are enforced by the backend rather than relying on the user interface.

### 3. Business Data Model

The project includes entities and database migrations for:

* Users
* Products
* Customers
* Sales
* Sale items
* Inventory movements
* Expenses
* Audit events

### 4. Database Management

* PostgreSQL integration.
* Spring Data JPA repositories.
* Flyway versioned migrations.
* Development seed data.
* Hibernate schema validation.

### 5. Monitoring

Spring Boot Actuator provides application health-check functionality.

The application is configured to expose selected management endpoints, including health and information endpoints.

## Project Structure

```text
sme-business-operating-system/
|
|-- backend/
|   |-- pom.xml
|   |
|   `-- src/
|       |-- main/
|       |   |-- java/com/lifted/smebos/
|       |   |   |-- config/
|       |   |   |-- controller/
|       |   |   |-- dto/
|       |   |   |-- entity/
|       |   |   |-- repository/
|       |   |   |-- security/
|       |   |   `-- SmeBosApplication.java
|       |   |
|       |   `-- resources/
|       |       |-- application.yml
|       |       |-- application.properties
|       |       `-- db/migration/
|       |
|       `-- target/          # Generated; excluded from Git
|
|-- docs/
|   |-- api-system-architecture.md
|   |-- business-rules-data-model.md
|   |-- database-design.md
|   |-- development-environment.md
|   |-- mvp-scope.md
|   |-- project-structure.md
|   |-- requirements.md
|   |-- security-design.md
|   `-- user-stories.md
|
|-- .env.example
|-- .gitignore
`-- README.md
```

## Getting Started

### Prerequisites

Install or configure the following:

* Java 21
* Maven 3.8.7 or a compatible version
* PostgreSQL 16, either locally or through Docker
* Git
* An Ubuntu/Linux development environment or an equivalent supported environment

### 1. Clone the repository

```bash
git clone https://github.com/LiftedDevOps-10/sme-business-operating-system.git

cd sme-business-operating-system
```

### 2. Configure the environment variables

The application expects the database password and JWT secret to be supplied through environment variables.

Create your local environment configuration without committing actual credentials.

For example, in an Ubuntu terminal:

```bash
export SMEBOS_DB_PASSWORD='your_local_database_password'
export SMEBOS_JWT_SECRET="$(openssl rand -base64 32)"
```

Replace the database password placeholder with the password configured for your PostgreSQL instance.

These commands set the variables for the current shell session. You must configure them again in a new session or use an appropriate local environment manager.

**Never commit real passwords, JWT secrets, or production credentials to GitHub.**

### 3. Prepare PostgreSQL

Create or configure a PostgreSQL database with these settings:

| Setting  | Expected value                            |
| -------- | ----------------------------------------- |
| Database | `smebos`                                  |
| Username | `smebos`                                  |
| Password | Your locally configured database password |
| Port     | `5432`                                    |

Ensure PostgreSQL is running and that the application can connect to it.

### 4. Build and test the application

From the repository root:

```bash
cd backend
mvn clean verify
```

This compiles the application, executes available automated tests, packages the application, and performs Maven verification.

### 5. Run the application

```bash
mvn spring-boot:run
```

The development configuration uses port `8085`.

The application expects the database and JWT environment variables to be configured before startup.

### 6. Check application health

Once the application is running, try:

```bash
curl -i http://localhost:8085/actuator/health
```

The expected result is a successful HTTP response containing health information, provided the application starts successfully and the endpoint is accessible.

## Security Notes

Security is a core part of the project.

The current implementation includes JWT authentication, Spring Security, role-based authorization, and environment-based secret configuration.

Development environments must be treated separately from production environments.

Important practices include:

* Keep actual secrets outside source control.
* Use strong, unique credentials.
* Do not use development seed accounts in production.
* Restrict administrative endpoints to authorized roles.
* Review logging to avoid exposing passwords, tokens, or other sensitive information.
* Apply least-privilege permissions to application and database accounts.
* Use HTTPS and appropriate secret management when deploying to production.

The project is under active development. Production readiness requires additional testing, security review, deployment hardening, and operational validation.

## Development Status

**Current stage:** Backend foundation and initial GitHub publication.

The repository contains the application foundation, security configuration, business data model, database migrations, and supporting documentation.

The initial Maven build and verification completed successfully in the development environment.

This does not, by itself, establish production readiness or confirm that every feature has comprehensive automated test coverage.

## Roadmap

Planned improvements include:

* [ ] Complete and test sales management workflows.
* [ ] Implement inventory management operations.
* [ ] Implement customer management workflows.
* [ ] Implement expense tracking.
* [ ] Expand sales and business analytics.
* [ ] Add comprehensive unit and integration tests.
* [ ] Add API documentation using OpenAPI/Swagger.
* [ ] Add GitHub Actions CI/CD workflows.
* [ ] Containerize the backend application.
* [ ] Provision cloud infrastructure using Terraform.
* [ ] Deploy the application to AWS.
* [ ] Add centralized logging, monitoring, and alerting.
* [ ] Strengthen production security and secret management.

## DevOps and Cloud Engineering Opportunities

This project provides a foundation for demonstrating practical DevOps skills as the delivery pipeline develops.

Potential extensions include:

* Automated builds and tests with GitHub Actions.
* Docker-based application packaging.
* Infrastructure as Code with Terraform.
* AWS deployment automation.
* Application health checks and monitoring.
* Security scanning and dependency management.
* Deployment documentation and operational runbooks.

These are roadmap items unless explicitly implemented and verified in the repository.

## Documentation

Additional project documentation is available in the [`docs/`](docs/) directory.

It covers requirements, user stories, database design, business rules, architecture, project structure, security design, and development environment setup.

## Author

**LiftedDevOps-10**

GitHub: https://github.com/LiftedDevOps-10

## License

No open-source license has been added yet. Unless a license is added, the repository should not be assumed to grant others permission to redistribute or modify the project.
