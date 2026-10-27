# SME Business Operating System — Development Environment

## 1. Purpose

This document defines the development tools, technologies, and
configuration used to build SME-BOS.

The goal is to provide a consistent and reproducible development
environment.

---

## 2. Operating Environment

Primary development environment:

- Windows host
- WSL2
- Ubuntu Linux

The application should be developed and tested primarily in the
Linux environment provided by WSL2.

---

## 3. Programming Language

### Java 21

Java 21 is the primary backend programming language.

Java will be used for:

- REST API development
- Business logic
- Database integration
- Authentication
- Authorization
- Automated testing

---

## 4. Backend Framework

### Spring Boot

Spring Boot will provide the backend application framework.

The backend will use Spring Boot for:

- REST APIs
- Dependency injection
- Configuration
- Security
- Database integration
- Application startup
- Monitoring integration

---

## 5. Build Tool

### Maven

Maven will manage:

- Dependencies
- Compilation
- Testing
- Packaging
- Application builds

Primary build command:

```text
mvn clean package
