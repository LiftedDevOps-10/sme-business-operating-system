# SME Business Operating System

# User Stories & Use Cases

## 1. User Roles

The system has three primary user roles:

- OWNER
- MANAGER
- STAFF

---

# 2. Owner User Stories

### US-OWNER-001 — Dashboard

As a business owner, I want to view my business dashboard so that I
can understand overall business performance.

### US-OWNER-002 — Product Management

As a business owner, I want to create and manage products so that my
business inventory remains accurate.

### US-OWNER-003 — Inventory

As a business owner, I want to view current stock levels so that I
know what products are available.

### US-OWNER-004 — Low Stock

As a business owner, I want to see products below their reorder levels
so that I can replenish stock.

### US-OWNER-005 — Sales

As a business owner, I want to view sales history so that I can
understand what my business has sold.

### US-OWNER-006 — Customers

As a business owner, I want to view customer purchase history so that
I can understand customer activity.

### US-OWNER-007 — Expenses

As a business owner, I want to view business expenses so that I can
understand where money is being spent.

### US-OWNER-008 — Users

As a business owner, I want to manage employee access so that only
authorized people can access business functions.

### US-OWNER-009 — Audit

As a business owner, I want to view important system activities so
that I can track operational changes.

---

# 3. Manager User Stories

### US-MANAGER-001 — Create Sale

As a manager, I want to create a sale so that customer purchases are
recorded.

### US-MANAGER-002 — Inventory

As a manager, I want to add stock so that inventory quantities remain
accurate.

### US-MANAGER-003 — Customer

As a manager, I want to create customers so that customer information
is maintained.

### US-MANAGER-004 — Expenses

As a manager, I want to record business expenses so that operational
spending is tracked.

### US-MANAGER-005 — Reports

As a manager, I want to view operational reports so that I can monitor
daily business activities.

---

# 4. Staff User Stories

### US-STAFF-001 — View Products

As a staff member, I want to search products so that I can quickly
find products during a customer transaction.

### US-STAFF-002 — Create Customer

As a staff member, I want to create a customer so that their
information can be associated with a sale.

### US-STAFF-003 — Create Sale

As a staff member, I want to record a customer sale so that the
transaction is stored.

### US-STAFF-004 — Inventory Visibility

As a staff member, I want to see available stock so that I do not
sell products that are unavailable.

---

# 5. Authentication User Stories

### US-AUTH-001

As a user, I want to log into SME-BOS so that I can access authorized
features.

### US-AUTH-002

As a user, I want to log out so that my account is protected when I
finish using the system.

### US-AUTH-003

As a user, I want unauthorized functions to be inaccessible so that
business data remains protected.

---

# 6. Use Cases

| ID | Use Case | Primary User |
|---|---|---|
| UC-001 | Login | All |
| UC-002 | View Dashboard | Owner |
| UC-003 | Create Product | Owner/Manager |
| UC-004 | Update Product | Owner/Manager |
| UC-005 | View Inventory | Owner/Manager/Staff |
| UC-006 | Add Stock | Owner/Manager |
| UC-007 | Create Customer | Owner/Manager/Staff |
| UC-008 | View Customer | Authorized users |
| UC-009 | Create Sale | Owner/Manager/Staff |
| UC-010 | View Sales | Owner/Manager |
| UC-011 | Record Expense | Owner/Manager |
| UC-012 | View Expenses | Owner/Manager |
| UC-013 | View Analytics | Owner/Manager |
| UC-014 | Manage Users | Owner |
| UC-015 | View Audit Events | Owner |

---

# 7. Primary Sales Workflow

```text
Customer arrives
      ↓
Search product
      ↓
Select product
      ↓
Enter quantity
      ↓
Check stock
      ↓
Available? ── No ──→ Reject sale
      │
     Yes
      ↓
Calculate total
      ↓
Select customer
      ↓
Confirm transaction
      ↓
Create sale
      ↓
Reduce inventory
      ↓
Update customer history
      ↓
Update analytics
      ↓
Record audit event
      ↓
Sale completed
