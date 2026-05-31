# User Service

Description
- User/profile management service for ConnectMe. Provides a read-only profile endpoint.



Environment variables (see `docker-compose.yml`)
- `SPRING_KAFKA_BOOTSTRAP_SERVERS` - Kafka bootstrap address
- `SPRING_KAFKA_CONSUMER_GROUP_ID` - Kafka consumer group id
- `TOKEN_SIGNING_KEY` - JWT signing key (shared across services)
- `SPRING_DATASOURCE_URL` - JDBC URL for the database
- `SPRING_DATASOURCE_USERNAME` - Database username
- `SPRING_DATASOURCE_PASSWORD` - Database password

Ports
- Exposed in docker-compose as `9091:8080` (host:container).

Usage / Endpoint

- GET /api/users/{username}
	- Description: Return public profile information for a user by username.
	- Example request:

```bash
curl http://localhost:9091/api/users/alice
```

	- Response (200):

```json
{
	"uuid": "<uuid>",
	"username": "alice",
	"createdAt": "2024-01-01T12:34:56Z"
}
```

	- Response (404): Not found if the username does not exist.
