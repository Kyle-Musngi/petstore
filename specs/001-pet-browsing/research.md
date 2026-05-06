# Research Notes: Pet Browsing

## Decision 1: Runtime and framework versions

- **Decision**: Java 21 LTS with Spring Boot 3.3.x; PostgreSQL 16; React 18.2.
- **Rationale**: Current LTS runtimes offer long support windows and align with
  modern Spring Boot and React ecosystems.
- **Alternatives considered**: Java 17 + Spring Boot 3.2, PostgreSQL 15, React 17.

## Decision 2: Frontend build tooling

- **Decision**: Vite for React build and dev server.
- **Rationale**: Fast local dev, simple static build output for Render static site.
- **Alternatives considered**: Create React App, Next.js.

## Decision 3: Backend testing stack

- **Decision**: JUnit 5 with Spring Boot Test and Testcontainers for Postgres.
- **Rationale**: Standard for Spring applications; Testcontainers matches
  production database behavior.
- **Alternatives considered**: H2 in-memory DB only, Mockito-only unit tests.

## Decision 4: Frontend testing stack

- **Decision**: Vitest with React Testing Library.
- **Rationale**: Matches Vite workflow and provides component-level coverage.
- **Alternatives considered**: Jest + React Testing Library.

## Decision 5: Render deployment layout

- **Decision**: Render Web Service for the API (Docker) and Render Static Site for
  the frontend; Render Postgres free-tier for database.
- **Rationale**: Aligns with free-tier constraints and clear separation of
  concerns.
- **Alternatives considered**: Single Docker service for API + UI, external DB.
