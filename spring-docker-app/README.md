# 🐳 Spring Boot + Docker — Learning Project

A **minimal Spring Boot REST API** built for Docker learning.
No prior Spring Boot knowledge needed — every line is commented!

---

## 📁 Project Structure

```
spring-docker-app/
│
├── Dockerfile                          ← Recipe to build the Docker image
├── pom.xml                             ← Maven config (dependencies & build)
│
└── src/main/
    ├── java/com/learning/app/
    │   ├── SpringDockerApp.java        ← Main class (entry point)
    │   ├── controller/
    │   │   └── HelloController.java    ← REST API endpoints
    │   └── model/
    │       └── Message.java           ← Data model (converts to JSON)
    │
    └── resources/
        └── application.yml            ← App configuration (YAML)
```

---

## 🌐 API Endpoints

| Method | URL | Description |
|---|---|---|
| GET | `/api/hello` | Returns a welcome message |
| GET | `/api/greet/{name}` | Returns a personalized greeting |
| GET | `/api/info` | Returns app info + Docker env check |
| GET | `/actuator/health` | Health check (for Docker) |

---

## 🚀 How to Run & Containerize

### Step 1 — Build the Docker Image
```bash
# Run this inside the spring-docker-app/ folder
docker build -t spring-docker-app .
```

### Step 2 — Run the Container
```bash
docker run -d -p 8080:8080 --name my-spring-app spring-docker-app
```

### Step 3 — Test the Endpoints
```bash
curl http://localhost:8080/api/hello
curl http://localhost:8080/api/greet/John
curl http://localhost:8080/api/info
curl http://localhost:8080/actuator/health
```

### Step 4 — View Container Logs
```bash
docker logs my-spring-app
```

### Step 5 — Stop & Remove Container
```bash
docker stop my-spring-app
docker rm my-spring-app
```

---

## 🔑 Key Concepts Learned

| Concept | Where |
|---|---|
| `@SpringBootApplication` | `SpringDockerApp.java` |
| `@RestController` + `@GetMapping` | `HelloController.java` |
| `@Value` (reading from YAML) | `HelloController.java` |
| YAML configuration | `application.yml` |
| Multi-stage Docker build | `Dockerfile` |
| Docker EXPOSE, ENV, ENTRYPOINT | `Dockerfile` |

