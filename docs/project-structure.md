# SME Business Operating System — Project Structure

## 1. Purpose

This document defines the technical project structure for SME-BOS.

The structure separates presentation, business logic, data access,
security, configuration, testing, infrastructure, and deployment.

---

## 2. Backend Architecture

The backend uses a layered Spring Boot architecture.

```text
REST Request
     |
     v
Controller
     |
     v
Service
     |
     v
Repository
     |
     v
PostgreSQL
