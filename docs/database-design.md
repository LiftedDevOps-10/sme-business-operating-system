# SME Business Operating System (SME-BOS)

# Database Design

## 1. Database Technology

Database:

PostgreSQL

The database will provide persistent storage for the SME-BOS application.

---

# 2. Core Tables

The MVP contains eight core tables:

1. users
2. products
3. inventory_movements
4. customers
5. sales
6. sale_items
7. expenses
8. audit_events

---

# 3. users

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| username | VARCHAR(100) | NOT NULL, UNIQUE |
| email | VARCHAR(255) | NOT NULL, UNIQUE |
| password_hash | VARCHAR(255) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |
| active | BOOLEAN | NOT NULL |
| created_at | TIMESTAMP | NOT NULL |
| updated_at | TIMESTAMP | NOT NULL |

Roles:

- OWNER
- MANAGER
- STAFF

---

# 4. products

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| name | VARCHAR(200) | NOT NULL |
| description | TEXT | |
| category | VARCHAR(100) | |
| purchase_price | NUMERIC(14,2) | NOT NULL, >= 0 |
| selling_price | NUMERIC(14,2) | NOT NULL, >= 0 |
| stock_quantity | INTEGER | NOT NULL, >= 0 |
| reorder_level | INTEGER | NOT NULL, >= 0 |
| supplier | VARCHAR(200) | |
| active | BOOLEAN | NOT NULL |
| created_at | TIMESTAMP | NOT NULL |
| updated_at | TIMESTAMP | NOT NULL |

---

# 5. customers

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| full_name | VARCHAR(200) | NOT NULL |
| phone | VARCHAR(30) | |
| email | VARCHAR(255) | |
| address | TEXT | |
| created_at | TIMESTAMP | NOT NULL |
| updated_at | TIMESTAMP | NOT NULL |

---

# 6. sales

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| customer_id | BIGINT | FK → customers.id |
| created_by | BIGINT | FK → users.id |
| total_amount | NUMERIC(14,2) | NOT NULL |
| payment_status | VARCHAR(20) | NOT NULL |
| sale_date | TIMESTAMP | NOT NULL |
| created_at | TIMESTAMP | NOT NULL |

---

# 7. sale_items

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| sale_id | BIGINT | FK → sales.id |
| product_id | BIGINT | FK → products.id |
| quantity | INTEGER | NOT NULL, > 0 |
| unit_price | NUMERIC(14,2) | NOT NULL, >= 0 |
| subtotal | NUMERIC(14,2) | NOT NULL, >= 0 |

---

# 8. inventory_movements

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| product_id | BIGINT | FK → products.id |
| movement_type | VARCHAR(30) | NOT NULL |
| quantity | INTEGER | NOT NULL |
| reference_id | BIGINT | |
| created_by | BIGINT | FK → users.id |
| created_at | TIMESTAMP | NOT NULL |

Movement types:

- STOCK_ADDITION
- SALE
- STOCK_ADJUSTMENT

---

# 9. expenses

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| category | VARCHAR(50) | NOT NULL |
| description | TEXT | |
| amount | NUMERIC(14,2) | NOT NULL, > 0 |
| expense_date | TIMESTAMP | NOT NULL |
| recorded_by | BIGINT | FK → users.id |
| created_at | TIMESTAMP | NOT NULL |

---

# 10. audit_events

| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| user_id | BIGINT | FK → users.id |
| action | VARCHAR(50) | NOT NULL |
| resource_type | VARCHAR(50) | NOT NULL |
| resource_id | BIGINT | |
| timestamp | TIMESTAMP | NOT NULL |
| details | TEXT | |

---

# 11. Relationships

users 1 → MANY sales

users 1 → MANY expenses

users 1 → MANY inventory_movements

users 1 → MANY audit_events

customers 1 → MANY sales

sales 1 → MANY sale_items

products 1 → MANY sale_items

products 1 → MANY inventory_movements

---

# 12. Indexes

Recommended indexes:

users:
- username
- email

products:
- name
- category
- active

customers:
- full_name
- phone
- email

sales:
- customer_id
- created_by
- sale_date

sale_items:
- sale_id
- product_id

inventory_movements:
- product_id
- created_at

expenses:
- category
- expense_date
- recorded_by

audit_events:
- user_id
- action
- timestamp

---

# 13. Data Integrity Rules

Product stock must never be negative.

Sale quantity must be greater than zero.

Expense amount must be greater than zero.

Product prices cannot be negative.

Every sale item must reference an existing sale.

Every sale item must reference an existing product.

Every sale must have an existing creator.

Every expense must have an existing recorder.

Important business operations should use database transactions.

---

# 14. Sale Transaction

A sale transaction should perform:

1. Validate products.
2. Validate quantities.
3. Check inventory.
4. Calculate subtotals.
5. Calculate total.
6. Create sale.
7. Create sale items.
8. Reduce inventory.
9. Create inventory movements.
10. Record audit event.
11. Commit transaction.

If any required operation fails:

ROLLBACK

---

# 15. Historical Price Preservation

The sale_items table stores unit_price separately from the current product selling_price.

This preserves the actual price charged when the sale occurred.

Example:

Product price today:

₦10,000

Historical sale:

₦8,500

The historical sale remains ₦8,500 even if the current product price changes.

---

# 16. Product Deactivation

Products should normally be deactivated rather than physically deleted.

Example:

active = false

This preserves historical sales information.

---

# 17. Database Technology Decisions

Database:

PostgreSQL

Backend:

Java 21 + Spring Boot

Persistence:

Spring Data JPA

Primary key:

BIGINT

Money:

NUMERIC(14,2)

Inventory quantity:

INTEGER

Timestamps:

TIMESTAMP

Passwords:

Hashed

Product removal:

Soft deactivation

Transactions:

Database transactions
