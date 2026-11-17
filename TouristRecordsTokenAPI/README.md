# Tourist Records API - STS Project

## Requirements
- JDK 17+
- Spring Tool Suite (STS)
- MySQL Server

## 1. Create database
Run this in MySQL Workbench:
```sql
CREATE DATABASE tourist_db;
```

## 2. Configure database password
Open `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD` with your MySQL root password.

## 3. Import into STS
1. Extract this ZIP.
2. Open STS.
3. Select File -> Import -> Maven -> Existing Maven Projects.
4. Select the extracted `TouristRecordsTokenAPI` folder.
5. Click Finish and wait for Maven dependencies to download.
6. Run `TouristRecordsApplication.java` as Spring Boot App.

Hibernate creates the `tourists` table automatically.

## 4. Add sample records
Run in MySQL Workbench:
```sql
USE tourist_db;
INSERT INTO tourists (name, age, email, phone, city, country) VALUES
('Arun Kumar', 24, 'arun@example.com', '9876543210', 'Hyderabad', 'India'),
('Priya Sharma', 22, 'priya@example.com', '9876501234', 'Vijayawada', 'India'),
('Rahul Reddy', 28, 'rahul@example.com', '9876512345', 'Bengaluru', 'India');
```

## 5. Swagger UI
Open: http://localhost:8080/swagger-ui/index.html

1. Click **Authorize**.
2. Enter this demo token (without the word Bearer):
   `TOURIST-ACCESS-2026-VALID`
3. Click Authorize, then Close.
4. Open `GET /api/tourists/all`, click Try it out, then Execute.
5. Valid token: HTTP 200 and JSON array of all records.
6. Missing/invalid token: HTTP 401 and JSON error.

## 6. Test with curl
Valid:
```bash
curl -H "Authorization: Bearer TOURIST-ACCESS-2026-VALID" http://localhost:8080/api/tourists/all
```
Invalid:
```bash
curl -i -H "Authorization: Bearer WRONG-TOKEN" http://localhost:8080/api/tourists/all
```

## Endpoint
`GET http://localhost:8080/api/tourists/all`

Header:
`Authorization: Bearer TOURIST-ACCESS-2026-VALID`

## Viva answers
1. **Why is token-based authorization essential when exposing bulk database records?**
   It ensures only clients possessing an approved token can access the endpoint. It reduces unauthorized data exposure, supports controlled access, and allows tokens to be rotated or revoked. In production, use HTTPS, short-lived tokens, secure secret storage, and role-based authorization where needed.

2. **What are the risks of returning unpaginated bulk records?**
   Large responses can consume memory, CPU, database connections, and bandwidth; cause slow responses or timeouts; expose excessive personal data; and make denial-of-service easier. Use pagination, filtering, rate limits, least-privilege access, and field-level data minimization for production systems.

## Notes
- The token is intentionally pre-generated and fixed for this classroom demonstration.
- The custom filter protects every `/api/**` endpoint.
- Swagger documentation is public so it can be opened; the actual API request still requires the token.
- Do not use a hard-coded token in production. Store secrets outside source control and use a proper identity provider or signed, expiring tokens.
