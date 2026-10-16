# Marvel Character REST API

## Project Overview

This project is a RESTful CRUD API developed using Java, Spring Boot, Spring Data JPA, and PostgreSQL. It manages Marvel characters and allows users to create, retrieve, update, delete, search, and filter character records.

The application uses a Neon PostgreSQL database for persistent storage and is deployed to Render using Docker.

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL (Neon)
- Docker
- Render
- Maven
- Git and GitHub

## Character Entity Attributes

| Attribute | Data Type | Description |
|---|---|---|
| id | Long | Unique auto-generated character identifier |
| name | String | Required character name; cannot be blank |
| description | String | Required character description; cannot be blank |
| alias | String | Character's superhero alias |
| universe | String | Character's universe, such as Marvel |

The `name` and `description` attributes use `@NotBlank` validation.

## REST API Endpoints

Base path: `/api/characters`

| HTTP Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/api/characters` | Retrieve all characters | 200 OK |
| GET | `/api/characters/{id}` | Retrieve a character by ID | 200 OK |
| POST | `/api/characters` | Create a new character | 201 Created |
| PUT | `/api/characters/{id}` | Update an existing character | 200 OK |
| DELETE | `/api/characters/{id}` | Delete a character | 204 No Content |
| GET | `/api/characters?name=pet` | Search characters by partial name, case-insensitive | 200 OK |
| GET | `/api/characters?universe=Marvel` | Filter characters by universe | 200 OK |

The API returns 404 Not Found when a requested character does not exist. Invalid request data returns 400 Bad Request.

## Example JSON Request

The following JSON can be submitted using POST `/api/characters`:

```json
{
  "name": "Peter Parker",
  "alias": "Spider-Man",
  "description": "A Marvel superhero with spider-like abilities.",
  "universe": "Marvel"
}
```

## Application Architecture

The application follows a layered architecture:

- **Entity:** Defines the Marvel character attributes and database mappings.
- **Repository:** Provides database access using Spring Data JPA.
- **Service:** Handles application logic and CRUD operations.
- **Controller:** Handles incoming HTTP requests and returns API responses.

## Database Configuration

The application stores character records in Neon PostgreSQL. Database credentials are provided using environment variables rather than being hardcoded in source code.

Required environment variables:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `PORT`

## Docker and Deployment

The application uses a multi-stage Dockerfile to build the Spring Boot project and run the packaged JAR using Java 21.

The API is deployed as a Render Web Service connected to the project's GitHub repository.

## De