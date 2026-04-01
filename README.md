# Bug Tracking and Resolution System

Java Spring Boot backend for a role-based bug tracking application with bug lifecycle management, audit history, dashboard statistics, and keyword-based priority suggestion.

## What This Project Does

This project exposes REST APIs for managing software bugs from reporting to resolution. It is designed around three user roles:

- `Admin`
- `Developer`
- `Tester`

The system supports:

- creating new bugs
- assigning bugs to developers
- updating bug status across the workflow
- tracking every status change in history
- viewing dashboard statistics
- suggesting priority from bug descriptions

## Feature Highlights

- Role-based login flow for Admin, Developer, and Tester users
- Full bug lifecycle support: create, assign, update, resolve, close
- Audit trail with `bug_history` records for every real status transition
- Dashboard statistics for total, open, resolved, and critical bugs
- Keyword-based priority suggestion engine for bug descriptions
- Backend support for filtering, reporting, and CSV-ready data export
- Automatic sample-user seeding on startup if users are missing

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Data Access | Spring Data JPA, Hibernate |
| Database | MySQL |
| Build Tool | Maven |
| API Style | REST API |

## Architecture

```text
Frontend (React)
      |
      v
Controllers
  |- AuthController
  `- BugController
      |
      v
Services
  |- UserService
  |- BugService
  `- PriorityEngine
      |
      v
Repositories
  |- UserRepository
  |- BugRepository
  `- BugHistoryRepository
      |
      v
MySQL Database
  |- users
  |- bugs
  `- bug_history
```

## Project Structure

```text
src/main/java/com/bugtracker/bugtracker_backend
|-- config
|   |-- CorsConfig.java
|   `-- SampleDataInitializer.java
|-- controller
|   |-- AuthController.java
|   `-- BugController.java
|-- entity
|   |-- Bug.java
|   |-- BugHistory.java
|   `-- User.java
|-- repository
|   |-- BugHistoryRepository.java
|   |-- BugRepository.java
|   `-- UserRepository.java
`-- service
    |-- BugService.java
    |-- PriorityEngine.java
    `-- UserService.java
```

## Main API Endpoints

### Authentication

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/auth/login` | Authenticate a user |
| `GET` | `/api/auth/users` | Return safe user data for UI dropdowns and views |

### Bug Management

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/api/bugs` | Get all bugs |
| `GET` | `/api/bugs/{id}` | Get one bug |
| `POST` | `/api/bugs` | Create a bug |
| `PUT` | `/api/bugs/{id}/status` | Update bug status |
| `GET` | `/api/bugs/{id}/history` | Get bug history |
| `GET` | `/api/bugs/stats` | Get dashboard statistics |
| `POST` | `/api/bugs/suggest-priority` | Suggest priority from description |
| `DELETE` | `/api/bugs/{id}` | Delete a bug |

## Sample Users

These users are seeded automatically if missing:

| Role | Username | Password |
|---|---|---|
| Admin | `admin` | `admin123` |
| Developer | `dev1` | `dev123` |
| Tester | `tester1` | `test123` |

## Local Setup

### 1. Database

Make sure MySQL is running and that a database named `bugtracker` exists.

Tracked config:

- `src/main/resources/application.properties`

Local-only password override:

- `src/main/resources/application-local.properties`

This keeps your real database password out of version control.

### 2. Run the Backend

Command Prompt:

```cmd
cd /d "D:\New folder\bugtracker-backend"
mvnw.cmd spring-boot:run
```

PowerShell:

```powershell
Set-Location "D:\New folder\bugtracker-backend"
.\mvnw.cmd spring-boot:run
```

Backend runs at:

- [http://localhost:8080](http://localhost:8080)

### 3. Quick API Checks

Open these in the browser:

- [http://localhost:8080/api/auth/users](http://localhost:8080/api/auth/users)
- [http://localhost:8080/api/bugs](http://localhost:8080/api/bugs)

## Frontend Note

This repository currently contains the backend service. The frontend is built separately in React and consumes these APIs for:

- login
- dashboard
- bug reporting
- bug detail and history
- statistics and export

## Resume-Friendly Summary

Built a role-based bug tracking backend using Java, Spring Boot, MySQL, and Spring Data JPA. Implemented bug lifecycle APIs, status history tracking, dashboard statistics, and a keyword-based priority suggestion engine for bug descriptions.
