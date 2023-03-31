# Docker-Configuration

create a file 'docker-compose.yml'

```
version: '3'
services:
  postgres:
    image: postgres
    hostname: postgres
    ports:
      - 5432:5432
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: example
    restart: unless-stopped

```

execute in bash:

```
docker compose -d
```

connect to database

```
jdbc:postgresql://localhost:5432/
```