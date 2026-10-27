# SME Business Operating System (SME-BOS)

## MVP Scope & Acceptance Criteria

**Project:** SME Business Operating System  
**Version:** MVP v1.0  
**Status:** Planning

---

# 1. MVP Objective

The objective of the SME-BOS MVP is to provide a centralized business management system that allows small and medium-sized businesses to manage:

- Authentication
- Users and roles
- Products
- Inventory
- Customers
- Sales
- Expenses
- Dashboard analytics
- Audit events

The MVP must support the complete core business workflow:

Create Product → Add Stock → Create Customer → Make Sale → Update Inventory → Update Customer History → Update Revenue → Record Expense → View Dashboard.

---

# 2. MVP Features

## 2.1 Authentication

The system must support:

- Login
- Logout
- Protected resources
- Role-based access control

Roles:

- OWNER
- MANAGER
- STAFF

---

## 2.2 Product Management

The system must support:

- Create products
- View products
- Search products
- Update products
- Deactivate products

Product information includes:

- Product ID
- Name
- Description
- Category
- Purchase price
- Selling price
- Stock quantity
- Reorder level
- Supplier
- Created date

---

## 2.3 Inventory Management

The system must:

- Display current stock
- Add stock
- Reduce stock after completed sales
- Prevent sales when stock is insufficient
- Identify low-stock products
- Maintain inventory movement history

---

## 2.4 Customer Management

The system must support:

- Create customers
- View customers
- Search customers
- Update customers
- View purchase history

Customer information includes:

- Customer ID
- Full name
- Phone
- Email
- Address
- Created date

---

## 2.5 Sales Management

The system must support:

- Create sales
- Add products to sales
- Specify quantities
- Calculate subtotals
- Calculate total sale amount
- Associate sales with customers
- Complete transactions
- View sales history

A completed sale must update inventory and customer purchase history.

---

## 2.6 Expense Management

The system must support recording expenses.

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

## 2.7 Dashboard & Analytics

The dashboard must display:

- Total revenue
- Total expenses
- Estimated operating profit
- Number of sales
- Number of customers
- Low-stock products
- Top-selling products

Estimated operating profit:

Revenue - Expenses

This is an operational estimate and is not intended to replace formal accounting or tax calculations.

---

## 2.8 Audit Logging

Important actions must generate audit events.

Initial audit events:

- LOGIN
- CREATE_PRODUCT
- UPDATE_PRODUCT
- ADD_STOCK
- CREATE_SALE
- CREATE_CUSTOMER
- CREATE_EXPENSE

Audit records should contain:

- User
- Action
- Timestamp
- Relevant resource

---

# 3. Out of Scope for MVP

The following features are planned for future versions:

- Supplier management
- Purchase orders
- Invoice PDF generation
- Multiple business branches
- Payroll
- Advanced accounting
- Customer notifications
- Email notifications
- SMS notifications
- Mobile application
- AI sales forecasting
- Advanced financial reporting
- Customer loyalty system

---

# 4. Acceptance Criteria

## AC-001 Login

Given a registered user has valid credentials,

When the user logs in,

Then the system authenticates the user and grants access according to their role.

---

## AC-002 Invalid Login

Given incorrect credentials,

When the user attempts to login,

Then authentication must fail and protected resources must remain inaccessible.

---

## AC-003 Create Product

Given an authorized user,

When a valid product is created,

Then the product must be stored and visible in the product list.

---

## AC-004 Add Inventory

Given a product has 10 units,

When 5 units are added,

Then the product stock must become 15 units.

---

## AC-005 Successful Sale

Given product stock is 10 units,

When a customer purchases 3 units,

Then:

- The sale succeeds.
- The remaining stock is 7 units.
- The sale appears in sales history.
- The customer purchase history is updated.
- Revenue is updated.
- An audit event is recorded.

---

## AC-006 Insufficient Stock

Given product stock is 2 units,

When a customer requests 5 units,

Then:

- The sale is rejected.
- No completed sale is created.
- Inventory remains at 2 units.

---

## AC-007 Customer Purchase History

Given a customer completes a purchase,

When the sale is successfully completed,

Then the sale must appear in the customer's purchase history.

---

## AC-008 Expense Recording

Given expenses of ₦100,000 and ₦20,000 are recorded,

Then total expenses must equal ₦120,000.

---

## AC-009 Revenue Calculation

Given completed sales of ₦500,000 and ₦200,000,

Then total revenue must equal ₦700,000.

---

## AC-010 Operating Profit Estimate

Given:

Revenue = ₦700,000

Expenses = ₦120,000

Then:

Estimated Operating Profit = ₦580,000.

---

## AC-011 Low Stock Detection

Given:

Current Stock = 5

Reorder Level = 10

Then the system must identify the product as LOW STOCK.

---

## AC-012 Role-Based Access

Given a user has a specific role,

When the user attempts to access a protected function,

Then the system must allow or deny access according to the defined role permissions.

---

## AC-013 Audit Event

Given an important business action occurs,

When the action is completed,

Then the system must create an audit event containing the user, action, timestamp, and relevant resource.

---

# 5. Primary MVP Business Workflow

The primary workflow is:

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
Calculate Sale Total
    ↓
Complete Sale
    ↓
Reduce Inventory
    ↓
Update Customer History
    ↓
Update Revenue
    ↓
Record Audit Event
    ↓
Dashboard Updated

---

# 6. MVP Success Definition

The MVP is considered functionally successful when a test user can:

1. Login.
2. Create a product.
3. Add inventory.
4. Create a customer.
5. Create a sale.
6. Have inventory automatically reduced.
7. View the sale in sales history.
8. View the transaction in customer history.
9. Record an expense.
10. View updated dashboard metrics.
11. See low-stock products.
12. Verify that the important actions were recorded in the audit log.
13. Demonstrate that insufficient stock prevents a sale.
14. Demonstrate that role permissions prevent unauthorized actions.

---

# 7. MVP Completion Checklist

- [ ] Authentication implemented
- [ ] User roles implemented
- [ ] Product management implemented
- [ ] Inventory management implemented
- [ ] Customer management implemented
- [ ] Sales management implemented
- [ ] Expense management implemented
- [ ] Dashboard implemented
- [ ] Basic analytics implemented
- [ ] Audit logging implemented
- [ ] Role-based authorization implemented
- [ ] Successful sale tested
- [ ] Insufficient stock tested
- [ ] Expense calculation tested
- [ ] Revenue calculation tested
- [ ] Low-stock detection tested
- [ ] End-to-end business workflow tested
