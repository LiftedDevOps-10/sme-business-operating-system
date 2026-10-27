# SME Business Operating System — Security Design

## 1. Purpose

This document defines the authentication, authorization, access control,
password protection, secrets management, and security rules for the
SME Business Operating System (SME-BOS).

The goal is to protect business data while allowing users to perform
the operations required by their roles.

---

## 2. Security Model

SME-BOS uses two major security concepts:

### Authentication

Authentication verifies the identity of a user.

Example:

Username + Password -> Authentication -> Access Token

### Authorization

Authorization determines whether an authenticated user is allowed
to perform a specific operation.

Example:

STAFF -> Create Sale -> Allowed

STAFF -> Record Expense -> Denied

---

## 3. User Roles

The MVP contains three roles:

- OWNER
- MANAGER
- STAFF

### OWNER

Full business access.

### MANAGER

Day-to-day business operations.

### STAFF

Front-line sales and customer operations.

---

## 4. Permission Matrix

| Function | OWNER | MANAGER | STAFF |
|---|---|---|---|
| Login | Yes | Yes | Yes |
| Logout | Yes | Yes | Yes |
| View Dashboard | Yes | Yes | Limited |
| Create Product | Yes | Yes | No |
| Update Product | Yes | Yes | No |
| Deactivate Product | Yes | Yes | No |
| View Products | Yes | Yes | Yes |
| View Inventory | Yes | Yes | Yes |
| Add Stock | Yes | Yes | Limited |
| Adjust Stock | Yes | Limited | No |
| View Inventory History | Yes | Yes | No |
| Create Customer | Yes | Yes | Yes |
| Update Customer | Yes | Yes | Yes |
| View Customer | Yes | Yes | Yes |
| Create Sale | Yes | Yes | Yes |
| View Sales | Yes | Yes | Limited |
| Record Expense | Yes | Yes | No |
| View Expenses | Yes | Yes | No |
| View Analytics | Yes | Yes | No |
| Manage Users | Yes | No | No |
| View Audit Logs | Yes | Limited | No |

Limited permissions will be defined precisely during implementation.

---

## 5. Authentication

The backend will use Spring Security.

The REST API will use token-based authentication.

Planned flow:

1. User submits username and password.
2. Spring Security locates the user.
3. The submitted password is compared with the stored password hash.
4. The account active status is checked.
5. Successful authentication produces an access token.
6. The client sends the token with protected API requests.

Example:

```text
Authorization: Bearer <token>
