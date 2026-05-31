# User Service

User profile service for ConnectMe.

Provides public profile information by username.


## Port

| Host | Container |
| ---- | --------- |
| 9091 | 8080      |

## API

| Method | Endpoint                | Description             |
| ------ | ----------------------- | ----------------------- |
| GET    | `/api/users/{username}` | Get public user profile |

### Response

```json
{
  "uuid": "<uuid>",
  "username": "alice",
  "createdAt": "2024-01-01T12:34:56Z"
}
```


