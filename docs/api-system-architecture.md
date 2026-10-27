# SME Business Operating System (SME-BOS)

# API & System Architecture

## 1. Architecture Overview

SME-BOS will use a layered application architecture.

The major layers are:

1. Frontend
2. REST API
3. Application/Business Logic
4. Data Access
5. PostgreSQL Database

---

# 2. High-Level Architecture

User
↓
Frontend
↓
REST API
↓
Spring Boot Backend
↓
PostgreSQL

---

# 3. Frontend

Initial frontend technology:

- HTML
- CSS
- JavaScript

Potential future enhancement:

- React

Frontend responsibilities:

- Display application screens
- Collect user input
- Call REST APIs
- Display API responses
- Display dashboard metrics
- Display validation errors

The frontend must not connect directly to PostgreSQL.

---

# 4. Backend

Backend technology:

- Java 21
- Spring Boot
- Maven
- Spring Data JPA
- Spring Security

Backend responsibilities:

- REST APIs
- Authentication
- Authorization
- Validation
- Business rules
- Transactions
- Calculations
- Database access
- Audit logging

---

# 5. Backend Layers

## Controller Layer

Receives HTTP requests and returns HTTP responses.

Example:

GET /api/products

POST /api/sales

---

## Service Layer

Contains business logic.

Examples:

- Check inventory
- Calculate sale totals
- Complete sales
- Update inventory
- Record expenses
- Generate dashboard metrics

---

## Repository Layer

Communicates with PostgreSQL.

Examples:

- Save product
- Find customer
- Find sales
- Save expense
- Find low-stock products

---

## Entity Layer

Represents database entities.

Core entities:

- User
- Product
- InventoryMovement
- Customer
- Sale
- SaleItem
- Expense
- AuditEvent

---

## DTO Layer

Data Transfer Objects define information exchanged through the API.

DTOs prevent clients from directly controlling internal database fields.

---

## Security Layer

Responsible for:

- Authentication
- Authorization
- Password hashing
- Role-based access control

---

## Exception Layer

Responsible for centralized error handling.

Example errors:

- Resource not found
- Validation failure
- Insufficient stock
- Unauthorized access
- Forbidden operation

---

# 6. REST API Base Path

All APIs use:

/api

---

# 7. Authentication APIs

POST /api/auth/login

POST /api/auth/logout

GET /api/auth/me

---

# 8. Product APIs

POST /api/products

GET /api/products

GET /api/products/{id}

PUT /api/products/{id}

PATCH /api/products/{id}/deactivate

---

# 9. Inventory APIs

GET /api/inventory

POST /api/inventory/stock

GET /api/inventory/low-stock

GET /api/inventory/products/{productId}/movements

---

# 10. Customer APIs

POST /api/customers

GET /api/customers

GET /api/customers/{id}

PUT /api/customers/{id}

GET /api/customers/{id}/sales

---

# 11. Sales APIs

POST /api/sales

GET /api/sales

GET /api/sales/{id}

---

# 12. Expense APIs

POST /api/expenses

GET /api/expenses

GET /api/expenses/{id}

GET /api/expenses/summary

---

# 13. Dashboard APIs

GET /api/dashboard

---

# 14. Analytics APIs

GET /api/analytics/revenue

GET /api/analytics/expenses

GET /api/analytics/top-products

GET /api/analytics/low-stock

---

# 15. Audit APIs

GET /api/audit-events

GET /api/audit-events/{id}

---

# 16. Main Sale Transaction

POST /api/sales

The sale process must:

1. Validate customer.
2. Validate products.
3. Validate quantities.
4. Check inventory.
5. Calculate subtotals.
6. Calculate total.
7. Create sale.
8. Create sale items.
9. Reduce inventory.
10. Create inventory movement records.
11. Record audit event.
12. Commit transaction.

If a required operation fails:

ROLLBACK

---

# 17. HTTP Status Codes

200 OK

201 Created

400 Bad Request

401 Unauthorized

403 Forbidden

404 Not Found

409 Conflict

500 Internal Server Error

---

# 18. API Error Format

Example:

{
  "timestamp": "2026-10-03T10:20:00",
  "status": 409,
  "error": "INSUFFICIENT_STOCK",
  "message": "Insufficient stock for Wireless Mouse",
  "path": "/api/sales"
}

---

# 19. Example Create Sale Request

POST /api/sales

{
  "customerId": 1,
  "items": [
    {
      "productId": 10,
      "quantity": 2
    },
    {
      "productId": 11,
      "quantity": 1
    }
  ],
  "paymentStatus": "PAID"
}

---

# 20. Example Sale Response

{
  "saleId": 1001,
  "customerId": 1,
  "totalAmount": 35000,
  "paymentStatus": "PAID",
  "status": "COMPLETED"
}

---

# 21. Architecture Principles

The system should follow these principles:

- Frontend must not access the database directly.
- Business rules belong in the service layer.
- Database access belongs in repositories.
- APIs should use clear resource-oriented paths.
- Passwords must never be stored as plain text.
- Important operations must be transactional.
- Errors should return meaningful responses.
- Authorization must be enforced server-side.
- Database credentials must not be hard-coded.
- Configuration should be externalized.

---

# 22. Future Infrastructure

The application will eventually be containerized using Docker.

Potential production architecture:

User
↓
HTTPS
↓
Frontend Container
↓
Backend Container
↓
Managed PostgreSQL Database

Supporting infrastructure may include:

- AWS networking
- IAM
- Secrets Manager
- CloudWatch
- Load balancing
- Terraform
- GitHub Actions

The exact production infrastructure will be defined during the infrastructure phase.
