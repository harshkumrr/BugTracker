# Bug Tracking and Resolution System

Backend for a bug tracking application built with Java, Spring Boot, MySQL, and Spring Data JPA.

## Overview

This project provides REST APIs for managing the complete bug lifecycle, including:

- bug creation
- bug assignment
- status updates
- bug history tracking
- dashboard statistics
- keyword-based priority suggestion

The system supports three roles:

- Admin
- Developer
- Tester

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Features

- REST APIs for bug management
- role-based login flow
- bug history audit trail
- dashboard statistics endpoints
- keyword-based priority suggestion engine
- deadline tracking support
- CSV/report-ready backend data support

## Project Structure

```text
src/main/java/com/bugtracker/bugtracker_backend
|-- config
|-- controller
|-- entity
|-- repository
`-- service
```

## Main API Endpoints

### Auth

- `POST /api/auth/login`
- `GET /api/auth/users`

### Bugs

- `GET /api/bugs`
- `GET /api/bugs/{id}`
- `POST /api/bugs`
- `PUT /api/bugs/{id}/status`
- `GET /api/bugs/{id}/history`
- `GET /api/bugs/stats`
- `POST /api/bugs/suggest-priority`
- `DELETE /api/bugs/{id}`

## Sample Users

- Admin: `admin / admin123`
- Developer: `dev1 / dev123`
- Tester: `tester1 / test123`

## Setup Instructions

### 1. Configure MySQL

Make sure MySQL is running and create a database named `bugtracker`.

Update your local password in:

`src/main/resources/application-local.properties`

Tracked config remains safe in:

`src/main/resources/application.properties`

### 2. Run the Backend

```bash
mvnw.cmd spring-boot:run
```

Backend will run on:

`http://localhost:8080`

### 3. Test the APIs

- `http://localhost:8080/api/bugs`
- `http://localhost:8080/api/auth/users`

## Notes

- The frontend is built separately and connects to this backend through REST APIs.
- Local secrets are kept out of version control using `application-local.properties`.
