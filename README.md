# BlueCollarHub

**BlueCollarHub** is a blue-collar gig matching engine built as a hands-on project to master **Clean Architecture**, **Domain-Driven Design (DDD)**, **Java 25**, and **Spring Boot 4.1.1**.

The platform models gig creation, worker calendar slot management with travel buffers, spatial/temporal availability checks, and real-time gig state transitions.

---

## Technical Stack & Principles

* **Language:** Java 25 (Virtual Threads, Records, Pattern Matching)
* **Framework:** Spring Boot 4.1.1
* **Build System:** Maven Multi-Module
* **Database:** PostgreSQL
* **Architecture:** Clean Architecture / Hexagonal Architecture with DDD principles
* **Guardrails:** ArchUnit (Enforcing pure domain boundaries)

---

## Project Structure

```plaintext
bluecollarhub/ (Parent POM)
├── bluecollarhub-domain/          # Pure Java core (Zero framework dependencies)
├── bluecollarhub-application/     # Use Cases and Port Interfaces
├── bluecollarhub-infrastructure/  # REST APIs, JPA Entities, PostgreSQL Adapters
└── bluecollarhub-bootstrap/       # Spring Boot Application Entry Point