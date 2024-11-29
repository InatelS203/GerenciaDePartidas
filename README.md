# GerenciaDePartidas
Sistema de recomendações de partidas

### Techs used
- Java;
- Spring Boot;
- Flyway;
- JPA;
- Postgres
- Lombok;
- RabbitMQ;

## Requirements

In order to run this project you have to install:

- [Docker](https://docs.docker.com/get-docker/);

## How to run

1. Clone the repository
```bash
git clone https://github.com/InatelS203/GerenciaDePartidas.git
```

2. Then open a command line tool in the following path: 
"matchManager\matchManager\"

Run Postgres and RabbitMQ first

```bash
docker-compose up -d postgres rabbitmq
```

3. Now, into your browser enter in the URL: 
"http://localhost:15672/"

Login with username="guest" and password="guest"

4. Go to the queue tab
![Queue tab in RabbitMQ](/documentation/queueTabInRabbitMQ.png)

5. Create the "user-queue" following the image
![Queue tab in RabbitMQ](/documentation/createQueueInRabbitMQ.png)

6. Do the same as in step 5, but for "user-score", "match-queue", "match-start", "match-finish"

7. Now, back into your commando line tool, run the app
 
```bash
docker-compose up -d app
```