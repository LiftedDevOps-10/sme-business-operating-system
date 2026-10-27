# Role-Based Access Control (RBAC) Test Report

## Project
SME Business Operating System (SME-BOS)

## Objective
Verify that authenticated users can access only the API endpoints permitted by their assigned roles.

## Test Environment
- Backend: Spring Boot
- Authentication: JSON Web Tokens (JWT)
- Authorization: Spring Security
- Roles: OWNER, MANAGER, STAFF
- Administrative endpoint: `GET /api/admin`
- Sales report endpoint: `GET /api/reports/sales`

## 1. Administrative API Tests

The administrative API is restricted to users with the OWNER role.

| Test ID | Role | Endpoint | Expected HTTP Status | Observed HTTP Status | Result |
|---|---|---|---:|---:|---|
| RBAC-001 | OWNER | GET /api/admin | 200 | 200 | PASS |
| RBAC-002 | MANAGER | GET /api/admin | 403 | 403 | PASS |
| RBAC-003 | STAFF | GET /api/admin | 403 | 403 | PASS |

## 2. Sales Report API Tests

The Sales Report API permits users with the OWNER and MANAGER roles.

| Test ID | Role | Endpoint | Expected HTTP Status | Observed HTTP Status | Result |
|---|---|---|---:|---:|---|
| RBAC-004 | OWNER | GET /api/reports/sales | 200 | 200 | PASS |
| RBAC-005 | MANAGER | GET /api/reports/sales | 200 | 200 | PASS |
| RBAC-006 | STAFF | GET /api/reports/sales | 403 | 403 | PASS |

## 3. Sales Report Validation Tests

| Test ID | Scenario | Expected HTTP Status | Observed HTTP Status | Result |
|---|---|---:|---:|---|
| API-001 | Retrieve the full sales report | 200 | 200 | PASS |
| API-002 | Retrieve a report for a valid date range | 200 | 200 | PASS |
| API-003 | Submit a start date later than the end date | 400 | 400 | PASS |
| API-004 | Supply only one date parameter | 400 | 400 | PASS |

## 4. Health Check

| Test ID | Endpoint | Expected HTTP Status | Observed HTTP Status | Result |
|---|---|---:|---:|---|
| HEALTH-001 | GET /actuator/health | 200 | 200 | PASS |

## 5. Security Findings

- The OWNER role can access the administrative API.
- MANAGER and STAFF are denied access to the administrative API.
- OWNER and MANAGER can access the Sales Report API.
- STAFF is denied access to the Sales Report API.
- Invalid or incomplete date-filter parameters are rejected with HTTP 400.
- The API returns HTTP 403 when an authenticated user lacks the required role.

## 6. Conclusion

All listed manual API tests passed in the development environment.

The observed responses are consistent with the intended role-based access rules.

These results document the tests performed; they do not constitute a complete security audit or penetration test.

## 7. Recommended Future Tests

- Verify that requests without a JWT are rejected on protected endpoints.
- Verify that expired, malformed, and invalid-signature JWTs are rejected.
- Add automated integration tests for each role and protected endpoint.
- Test object-level authorization to ensure users cannot access records outside their permitted scope.
- Run dependency, configuration, and application security checks before production deployment.
