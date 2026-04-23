# SafeHealth API

SafeHealth is a robust RESTful API built to manage healthcare operations, including appointments, patients, and doctors. It is designed to demonstrate modern software engineering principles and architectural patterns suitable for enterprise-grade environments.

## Tech Stack

*   **Language:** Java 21
*   **Framework:** Spring Boot (Web, Data JPA, Validation)
*   **Database:** PostgreSQL (Production) / H2 (Development & Testing)
*   **Utilities:** Lombok, Maven

## Key Differentiators

Unlike basic CRUD applications, this project is designed for scalability and maintainability, implementing advanced software design patterns:

*   **Hexagonal Architecture:** Strict separation of concerns across Domain, Application, and Infrastructure layers. This ensures the core business logic is completely decoupled from external frameworks, databases, and delivery mechanisms.
*   **Data Transfer Objects (DTOs):** Consistent isolation of request and response payloads, ensuring internal domain models are never directly exposed to the external API clients.
*   **RESTful Best Practices:** Proper usage of HTTP status codes, well-defined URI structures, and strict input validation using Jakarta Validation annotations.
*   **Clean Code:** Highly readable, maintainable codebase adhering to the Single Responsibility Principle, with dedicated mappers and isolated exception handling.

## How to Run

### Prerequisites

*   Java 21 Development Kit (JDK)
*   Maven (or use the included Maven wrapper)

### Execution Steps

1.  **Navigate to the project directory:**
    ```bash
    cd safehealthskm
    ```

2.  **Build the project:**
    Compile the code and download dependencies.
    ```bash
    ./mvnw clean install
    ```

3.  **Run the application:**
    Start the Spring Boot server. By default, it will run on port 8080.
    ```bash
    ./mvnw spring-boot:run
    ```

*Note: Ensure your database configuration in `src/main/resources/application.properties` is correctly set up. The project includes an H2 database runtime dependency for immediate local testing without an external database instance.*

## Endpoints Overview

The application features a fully documented REST interface. Below are the primary resources available:

**Appointments (`/api/citas`)**
*   `POST /api/citas` - Schedule a new appointment
*   `GET /api/citas/{id}` - Retrieve appointment details by ID
*   `GET /api/citas/paciente/{pacienteId}` - List all appointments for a specific patient
*   `GET /api/citas/medico/{medicoId}` - List all appointments for a specific doctor
*   `PATCH /api/citas/{id}/estado` - Update the status of an existing appointment
*   `DELETE /api/citas/{id}` - Cancel an appointment

**Doctors (`/api/medicos`)**
*   Endpoints to manage doctor profiles and specialties.

**Patients (`/api/pacientes`)**
*   Endpoints to manage patient profiles and demographic data.
