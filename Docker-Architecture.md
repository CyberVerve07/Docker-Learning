# 🏗️ Docker Architecture

---

## Overview

Docker uses a **Client-Server Architecture**. The Docker client talks to the Docker daemon, which does the heavy lifting of building, running, and managing containers.

```
┌─────────────────────────────────────────────────────────────┐
│                        DOCKER HOST                          │
│                                                             │
│   ┌──────────────┐        ┌──────────────────────────────┐  │
│   │              │        │       Docker Daemon           │  │
│   │ Docker Client│◄──────►│         (dockerd)            │  │
│   │  (docker CLI)│  REST  │                              │  │
│   │              │  API   │  ┌──────────┐ ┌──────────┐  │  │
│   └──────────────┘        │  │Container │ │Container │  │  │
│                           │  └──────────┘ └──────────┘  │  │
│                           │                              │  │
│                           │  ┌──────────────────────┐   │  │
│                           │  │       Images         │   │  │
│                           │  └──────────────────────┘   │  │
│                           └──────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
                        ┌───────────────────────┐
                        │     Docker Registry    │
                        │      (Docker Hub)      │
                        └───────────────────────┘
```

---

## Core Components

### 1. 🖥️ Docker Client
- The **primary way users interact** with Docker
- When you type `docker run`, `docker build`, etc. — you are using the Docker client
- It sends commands to the **Docker Daemon** via a REST API
- Can communicate with **more than one daemon**

```bash
# You → Docker Client → Docker Daemon
docker run nginx
```

---

### 2. ⚙️ Docker Daemon (`dockerd`)
- The **heart of Docker** — runs in the background as a system service
- Listens for Docker API requests
- Manages all Docker objects: **images, containers, networks, volumes**
- Can communicate with other daemons to manage **distributed services**

---

### 3. 📦 Docker Images
- A **read-only template** used to create containers
- Built using a **Dockerfile** — a text file with step-by-step instructions
- Images are **layered** — each instruction in a Dockerfile adds a new layer

```
┌─────────────────────┐
│    Your App Layer   │  ← LAYER 4 (your code)
├─────────────────────┤
│   Dependencies      │  ← LAYER 3 (npm install, pip install)
├─────────────────────┤
│   Runtime (Node/    │  ← LAYER 2 (language runtime)
│   Python/Java)      │
├─────────────────────┤
│   Base OS (Ubuntu,  │  ← LAYER 1 (base image)
│   Alpine, etc.)     │
└─────────────────────┘
```

---

### 4. 🚢 Docker Containers
- A **running instance of an image**
- Lightweight, isolated, and portable
- Has its own filesystem, networking, and process space
- Multiple containers can run from the **same image** without conflict

```
Image  ──────►  Container 1 (running)
       ──────►  Container 2 (running)
       ──────►  Container 3 (stopped)
```

---

### 5. 🗄️ Docker Registry
- A **storage and distribution system** for Docker images
- **Docker Hub** is the default public registry (like GitHub but for images)
- You can also host a **private registry**

| Registry Type | Example |
|---|---|
| Public Registry | Docker Hub (hub.docker.com) |
| Private Registry | AWS ECR, Google GCR, Azure ACR |
| Self-Hosted | Harbor, Nexus |

```bash
docker pull nginx        # Download from Docker Hub
docker push myimage      # Upload to Docker Hub
```

---

### 6. 🌐 Docker Networking
Docker provides several network drivers so containers can communicate:

| Network Type | Description |
|---|---|
| `bridge` | Default. Containers on the same host communicate via bridge network |
| `host` | Container shares the host's network directly |
| `none` | No networking — fully isolated |
| `overlay` | Multi-host networking for Docker Swarm |

---

### 7. 💾 Docker Volumes
- Used for **persistent data storage**
- Data in a container is lost when the container stops — volumes solve this
- Volumes live **outside** the container filesystem

```bash
docker run -v myvolume:/app/data nginx
#              ▲             ▲
#         volume name    container path
```

---

## How It All Works Together

```
YOU
 │
 │  type: docker run nginx
 ▼
Docker Client
 │
 │  sends REST API request
 ▼
Docker Daemon (dockerd)
 │
 ├── Checks if "nginx" image exists locally
 │       │
 │       ├── YES → Use local image
 │       │
 │       └── NO  → Pull from Docker Hub (Registry)
 │                        │
 │                        ▼
 │                   Downloads Image
 │
 ├── Creates a Container from the image
 │
 ├── Allocates Network & Storage
 │
 └── Starts the Container ✅
```

---

## Docker vs Virtual Machine

| Feature | Docker Container | Virtual Machine |
|---|---|---|
| Boot Time | Seconds | Minutes |
| Size | MBs | GBs |
| OS | Shares host OS kernel | Full OS per VM |
| Isolation | Process-level | Hardware-level |
| Performance | Near-native | Overhead |
| Portability | Very High | Moderate |

```
┌─────────────────────┐     ┌─────────────────────┐
│    DOCKER           │     │   VIRTUAL MACHINE    │
│  ┌───┐ ┌───┐ ┌───┐ │     │  ┌───────────────┐  │
│  │App│ │App│ │App│ │     │  │  App + Full OS│  │
│  └───┘ └───┘ └───┘ │     │  └───────────────┘  │
│  Docker Engine      │     │  Hypervisor         │
│  Host OS Kernel     │     │  Host OS            │
│  Infrastructure     │     │  Infrastructure     │
└─────────────────────┘     └─────────────────────┘
   Lightweight ✅               Heavy ❌
```

---

> **Next Step:** Learn how to write a `Dockerfile` to build your own custom images.

