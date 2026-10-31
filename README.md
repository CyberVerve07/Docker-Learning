# 🐳 Docker — Learn From Scratch

---

## What is Docker?

Docker is an open-source platform that lets you **package, ship, and run applications inside containers**.

A **container** is a lightweight, standalone unit that includes everything an application needs to run:
- Code
- Runtime
- Libraries
- Configuration

---

## The Problem Docker Solves

### ❌ Before Docker — "It works on my machine!"

Imagine you build an app on your laptop. It works perfectly. But when you hand it to a teammate or deploy it to a server — it **breaks**.

Why? Because:
- Different OS versions
- Different library/dependency versions
- Different environment configurations
- Missing system tools

Every developer had to manually set up the same environment — and even then, subtle differences caused bugs. Deploying to production was painful, slow, and error-prone.

### ✅ After Docker — "Ship the environment, not just the code"

Docker solves this by packaging the **application + its entire environment** into a single container.

| Without Docker | With Docker |
|---|---|
| "It works on my machine" | Works the same everywhere |
| Manual environment setup | One command: `docker run` |
| Dependency conflicts | Isolated containers, no conflicts |
| Slow, fragile deployments | Fast, consistent deployments |
| Hard to scale | Scale containers in seconds |

---

## 20 Important Docker Commands

| # | Command | Description |
|---|---|---|
| 1 | `docker --version` | Check the installed Docker version |
| 2 | `docker pull <image>` | Download an image from Docker Hub |
| 3 | `docker images` | List all locally available images |
| 4 | `docker run <image>` | Create and start a container from an image |
| 5 | `docker run -d <image>` | Run a container in detached (background) mode |
| 6 | `docker run -p 8080:80 <image>` | Map host port 8080 to container port 80 |
| 7 | `docker run --name myapp <image>` | Run a container with a custom name |
| 8 | `docker ps` | List all running containers |
| 9 | `docker ps -a` | List all containers (including stopped ones) |
| 10 | `docker stop <container>` | Gracefully stop a running container |
| 11 | `docker start <container>` | Start a stopped container |
| 12 | `docker restart <container>` | Restart a container |
| 13 | `docker rm <container>` | Remove a stopped container |
| 14 | `docker rmi <image>` | Remove a Docker image |
| 15 | `docker exec -it <container> bash` | Open an interactive shell inside a running container |
| 16 | `docker logs <container>` | View logs/output of a container |
| 17 | `docker build -t myimage .` | Build a Docker image from a Dockerfile |
| 18 | `docker-compose up` | Start all services defined in `docker-compose.yml` |
| 19 | `docker-compose down` | Stop and remove all services from compose |
| 20 | `docker system prune` | Remove all unused containers, images, and networks |

---

> **Next Step:** Learn how to write a `Dockerfile` to build your own custom images.

