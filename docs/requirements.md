# SME Business Operating System

## Business & Technical Requirements

### 1. Project Overview

SME Business Operating System (SME-BOS) is a centralized business
management platform designed to help small and medium-sized businesses
manage sales, inventory, customers, expenses, and business analytics.

---

## 2. Business Problem

Small and medium-sized businesses often manage sales, inventory,
expenses, and customer information using disconnected tools such as
spreadsheets, notebooks, messaging applications, and manual processes.

This fragmentation can make it difficult to maintain accurate records,
monitor inventory, understand financial performance, and make timely
business decisions.

SME-BOS addresses this problem by providing a centralized platform
for managing core business operations.

---

## 3. Target Users

The initial target users are product-based SMEs such as:

- Retail businesses
- Electronics stores
- Fashion businesses
- Supermarkets
- Wholesale businesses
- Other small product-based businesses

---

## 4. User Roles

### Owner

The owner can:

- View business dashboard
- Manage products
- Manage inventory
- Manage customers
- View sales
- Manage expenses
- View analytics
- Manage users

### Manager

The manager can:

- Create sales
- Manage inventory
- Manage customers
- View reports
- Record expenses

### Staff

Staff can:

- Create sales
- View products
- Update stock where authorized
- Create customers

---

# 5. Business Requirements

### BR-001 — Centralized Business Management

The system shall provide a centralized platform for managing core
business operations.

### BR-002 — Sales Management

The system shall allow businesses to record and manage sales
transactions.

### BR-003 — Inventory Management

The system shall allow businesses to monitor and manage product
inventory.

### BR-004 — Customer Management

The system shall allow businesses to maintain customer information
and purchase history.

### BR-005 — Expense Management

The system shall allow businesses to record and categorize
operational expenses.

### BR-006 — Business Analytics

The system shall provide business performance metrics based on
sales, expenses, customers, and inventory data.

### BR-007 — Secure Access

The system shall restrict business information to authorized users.

### BR-008 — Operational Visibility

The system shall provide visibility into important business
conditions such as low inventory.

---

# 6. Functional Requirements

## 6.1 Authentication

- FR-AUTH-001: Users shall be able to log in.
- FR-AUTH-002: Users shall be able to log out.
- FR-AUTH-003: Protected resources shall require authentication.
- FR-AUTH-004: The system shall support role-based access control.

Initial roles:

- OWNER
- MANAGER
- STAFF

---

## 6.2 Product Management

- FR-PROD-001: Authorized users shall be able to create products.
- FR-PROD-002: Authorized users shall be able to update products.
- FR-PROD-003: Authorized users shall be able to deactivate products.
- FR-PROD-004: Users shall be able to search and view products.

Product information should include:

- Product ID
- Product name
- Description
- Category
- Purchase price
- Selling price
- Stock quantity
- Reorder level
- Supplier
- Created date

---

## 6.3 Inventory Management

- FR-INV-001: The system shall maintain current product quantities.
- FR-INV-002: Authorized users shall be able to add stock.
- FR-INV-003: Completed sales shall automatically reduce inventory.
- FR-INV-004: The system shall prevent sales when stock is insufficient.
- FR-INV-005: The system shall identify low-stock products.
- FR-INV-006: The system shall maintain inventory movement history.

---

## 6.4 Sales Management

- FR-SALES-001: Authorized users shall be able to create sales.
- FR-SALES-002: Sales shall contain customer, product, quantity,
  price, total, payment status, and date information.
- FR-SALES-003: The system shall automatically calculate sale totals.
- FR-SALES-004: Sales shall be associated with customers when provided.
- FR-SALES-005: Successful sales shall update inventory.
- FR-SALES-006: The system shall maintain sales history.

---

## 6.5 Customer Management

- FR-CUST-001: Authorized users shall be able to create customers.
- FR-CUST-002: Users shall be able to update customers.
- FR-CUST-003: Users shall be able to search customers.
- FR-CUST-004: The system shall display customer purchase history.

Customer information should include:

- Customer ID
- Full name
- Phone
- Email
- Address
- Created date

---

## 6.6 Expense Management

- FR-EXP-001: Authorized users shall be able to record expenses.
- FR-EXP-002: Expenses shall contain category, description, amount,
  date, and user information.
- FR-EXP-003: The system shall support expense categories.
- FR-EXP-004: Users shall be able to view expense history.
- FR-EXP-005: The system shall calculate total expenses for a
  selected period.

Initial expense categories:

- RENT
- UTILITIES
- SALARIES
- TRANSPORT
- MARKETING
- SUPPLIES
- MAINTENANCE
- OTHER

---

## 6.7 Dashboard and Analytics

- FR-ANALYTICS-001: The system shall display total revenue.
- FR-ANALYTICS-002: The system shall display total expenses.
- FR-ANALYTICS-003: The system shall calculate estimated operating
  profit.
- FR-ANALYTICS-004: The system shall display number of sales.
- FR-ANALYTICS-005: The system shall display number of customers.
- FR-ANALYTICS-006: The system shall identify low-stock products.
- FR-ANALYTICS-007: The system shall identify top-selling products.

Estimated operating profit:

Revenue - Expenses

This metric is an operational estimate and is not intended to replace
formal accounting or tax calculations.

---

## 6.8 Audit Requirements

The system should record important business actions, including:

- LOGIN
- CREATE_PRODUCT
- UPDATE_PRODUCT
- ADD_STOCK
- CREATE_SALE
- CREATE_CUSTOMER
- CREATE_EXPENSE

Audit records should include the user, action, timestamp, and
relevant resource where applicable.

---

# 7. Non-Functional Requirements

## NFR-001 — Security

The system shall protect sensitive business information from
unauthorized access.

Security controls will include authentication, authorization,
secure secrets management, encryption, and security scanning.

## NFR-002 — Availability

The application should remain available during normal operating
conditions.

## NFR-003 — Performance

Normal API requests should provide acceptable response times under
expected MVP workloads.

Performance targets will be validated through testing.

## NFR-004 — Scalability

The architecture should support increased traffic and business data
without requiring a complete redesign.

## NFR-005 — Observability

The system shall provide:

- Application logs
- Infrastructure logs
- Metrics
- Health checks
- Alerts

## NFR-006 — Reliability

The system should minimize data loss and support recovery from
application or infrastructure failures.

## NFR-007 — Maintainability

The application should use a modular structure that allows
components to be maintained independently.

## NFR-008 — Automation

Application testing, building, security scanning, and deployment
should be automated through CI/CD.

## NFR-009 — Infrastructure as Code

AWS infrastructure shall be managed using Terraform.

## NFR-010 — Documentation

The project shall document architecture, deployment, security,
operations, and troubleshooting procedures.

---

# 8. MVP Scope

The initial MVP will include:

- Authentication
- User roles
- Product management
- Inventory management
- Customer management
- Sales management
- Expense management
- Dashboard
- Basic analytics
- Audit events

---

# 9. Future Features

Potential future features include:

- Supplier management
- Purchase orders
- Invoice PDF generation
- Multiple business branches
- Payroll
- Advanced accounting
- Customer notifications
- Email/SMS notifications
- Mobile application
- AI-based sales forecasting

These features are outside the initial MVP scope.

---

# 10. Key Business Workflow

The primary business workflow is:

Create Product
    ↓
Add Stock
    ↓
Create Customer
    ↓
Create Sale
    ↓
Check Available Stock
    ↓
Complete or Reject Sale
    ↓
Update Inventory
    ↓
Update Customer History
    ↓
Update Revenue
    ↓
Update Dashboard

---

# 11. Acceptance Criteria

### Successful Sale

Given a product has 10 units in stock,
when a customer purchases 3 units,
the sale should succeed and remaining stock should be 7.

### Insufficient Stock

Given a product has 2 units in stock,
when a customer requests 5 units,
the sale should be rejected and stock should remain 2.

### Expense

Given total expenses are ₦100,000,
when a user records a ₦20,000 expense,
total expenses should become ₦120,000.

### Low Stock

Given a product has 5 units and its reorder level is 10,
the product should be identified as low stock.

---

# 12. Project Success Criteria

The MVP will be considered successful when a user can:

1. Authenticate.
2. Create a product.
3. Add inventory.
4. Create a customer.
5. Complete a sale.
6. Automatically reduce inventory.
7. Update customer purchase history.
8. Record an expense.
9. View updated business metrics.
10. Observe application activity through logs and monitoring.

---

# 13. Planned Technology Stack

### Application

- Java 21
- Spring Boot
- Maven
- REST API
- PostgreSQL

### Containerization

- Docker
- Docker Compose

### Cloud

- AWS

### Infrastructure

- Terraform

### CI/CD

- Git
- GitHub
- GitHub Actions

### Security

- AWS IAM
- AWS Secrets Manager
- Dependency scanning
- Container scanning

### Monitoring

- Amazon CloudWatch
- Application logs
- Metrics
- Alarms
