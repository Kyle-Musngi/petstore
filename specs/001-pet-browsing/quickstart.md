# Quickstart: Pet Browsing

## Prerequisites

- Docker and Docker Compose
- Node.js 20+
- Java 21
- Gradle 8+

## Environment Variables

Create `.env` files for local development (copy from the provided `.env.example` files).

### backend/.env

```
SPRING_PROFILES_ACTIVE=local
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=petstore
POSTGRES_USER=petstore
POSTGRES_PASSWORD=petstore
```

### frontend/.env

```
VITE_API_BASE_URL=http://localhost:8080/musngi/api/v1
```

## Local Development

1. Start the database:

```
docker compose up -d db
```

2. Start the backend:

```
./gradlew bootRun
```

3. Start the frontend:

```
cd frontend
npm install
npm run dev
```

4. Open the app:

- Frontend: http://localhost:5173
- Backend API: http://localhost:8080/musngi/api/v1

## Seed Data

- Run the database migration task (Flyway/Liquibase) during backend startup.
- Ensure seed data includes all pet categories for demo coverage.
