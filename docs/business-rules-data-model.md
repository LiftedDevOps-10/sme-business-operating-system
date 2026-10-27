# SME Business Operating System (SME-BOS)

# Business Rules & Data Model

## 1. Purpose

This document defines the core business rules, entities, relationships, and data constraints for the SME Business Operating System.

---

# 2. Core Business Rules

## BR-001 Product Identity

Every product must have a unique Product ID.

## BR-002 Product Pricing

Product purchase and selling prices cannot be negative.

## BR-003 Non-Negative Inventory

Product inventory must never become negative.

## BR-004 Sale Must Contain Items

Every completed sale must contain at least one sale item.

## BR-005 Positive Sale Quantity

Sale quantity must be greater than zero.

## BR-006 Successful Sale Updates Inventory

A completed sale must reduce product inventory by the quantity sold.

## BR-007 Failed Sale Does Not Modify Inventory

If a sale fails because of insufficient stock, inventory must remain unchanged.

## BR-008 Automatic Sale Calculation

Sale totals must be calculated by the system.

Subtotal:

quantity × unit price

Total:

sum of all sale item subtotals

## BR-009 Customer Purchase History

Completed customer sales must be available in the customer's purchase history.

## BR-010 Positive Expense

Expense amounts must be greater than zero.

## BR-011 Low Stock Detection

A product is considered low stock when:

current stock <= reorder level

## BR-012 Role-Based Access

System functionality must be restricted according to user roles.

Roles:

- OWNER
- MANAGER
- STAFF

## BR-013 Audit Logging

Important business actions must generate audit events.

## BR-014 Product Deactivation

Products should normally be deactivated rather than physically deleted so historical sales remain understandable.

---

# 3. Core Entities

## 3.1 User

Fields:

- id
- username
- email
- password
- role
- active
- created_at
- updated_at

---

## 3.2 Product

Fields:

- id
- name
- description
- category
- purchase_price
- selling_price
- stock_quantity
- reorder_level
- supplier
- active
- created_at
- updated_at

---

## 3.3 InventoryMovement

Fields:

- id
- product_id
- movement_type
- quantity
- reference_id
- created_by
- created_at

Movement examples:

- STOCK_ADDITION
- SALE
- STOCK_ADJUSTMENT

---

## 3.4 Customer

Fields:

- id
- full_name
- phone
- email
- address
- created_at
- updated_at

---

## 3.5 Sale

Fields:

- id
- customer_id
- created_by
- total_amount
- payment_status
- sale_date
- created_at

---

## 3.6 SaleItem

Fields:

- id
- sale_id
- product_id
- quantity
- unit_price
- subtotal

---

## 3.7 Expense

Fields:

- id
- category
- description
- amount
- expense_date
- recorded_by
- created_at

---

## 3.8 AuditEvent

Fields:

- id
- user_id
- action
- resource_type
- resource_id
- timestamp
- details

---

# 4. Entity Relationships

User → Sales

One user can create many sales.

User → Expenses

One user can record many expenses.

User → Audit Events

One user can generate many audit events.

Customer → Sales

One customer can have many sales.

Sale → Sale Items

One sale contains one or more sale items.

Product → Sale Items

One product can appear in many sale items.

Product → Inventory Movements

One product can have many inventory movements.

---

# 5. Relationship Summary

USER 1 → MANY SALES

USER 1 → MANY EXPENSES

USER 1 → MANY AUDIT EVENTS

CUSTOMER 1 → MANY SALES

SALE 1 → MANY SALE ITEMS

PRODUCT 1 → MANY SALE ITEMS

PRODUCT 1 → MANY INVENTORY MOVEMENTS

---

# 6. Database Constraints

## User

- username must be unique
- email must be unique
- role cannot be null

## Product

- name cannot be null
- purchase price >= 0
- selling price >= 0
- stock quantity >= 0
- reorder level >= 0

## Customer

- full name cannot be null

## Sale

- customer must exist where required
- creator must exist
- total amount cannot be negative
- sale date required

## SaleItem

- sale must exist
- product must exist
- quantity > 0
- unit price >= 0
- subtotal >= 0

## Expense

- category required
- amount > 0
- expense date required

---

# 7. Sale Transaction Integrity

A completed sale should be processed as one database transaction.

The transaction includes:

1. Create sale
2. Create sale items
3. Validate inventory
4. Reduce inventory
5. Create inventory movement records
6. Update transaction-related records
7. Record audit event

If all operations succeed:

COMMIT

If a required operation fails:

ROLLBACK

This prevents partial transactions and inconsistent business data.

---

# 8. Inventory Rule

Inventory must never become negative.

Example:

Stock = 10

Sale quantity = 3

Result:

Stock = 7

If:

Stock = 2

Sale quantity = 5

Result:

Sale rejected.

Stock remains:

2

---

# 9. Low Stock Rule

A product is low stock when:

Current Stock <= Reorder Level

Example:

Current Stock = 5

Reorder Level = 10

Result:

LOW STOCK

---

# 10. Core Business Transaction

The primary business transaction is:

Create Product
→ Add Stock
→ Create Customer
→ Create Sale
→ Validate Stock
→ Calculate Total
→ Complete Sale
→ Reduce Inventory
→ Record Inventory Movement
→ Update Customer History
→ Update Analytics
→ Record Audit Event

---

# 11. Future Extensions

Potential future entities:

- Supplier
- PurchaseOrder
- PurchaseOrderItem
- Invoice
- Branch
- Employee
- Payroll
- Notification
- CustomerLoyalty
