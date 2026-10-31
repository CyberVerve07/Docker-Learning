# ============================================================
#  Dockerfile — Instructions to build a Docker Image
# ============================================================
#
#  A Dockerfile is like a RECIPE for Docker.
#  Docker reads this file top-to-bottom and creates an IMAGE.
#
#  Each line = one LAYER in the image.
#  Layers are cached — unchanged layers are reused (faster builds).
# ============================================================


# ---- STAGE 1: BUILD STAGE ----
# We use a multi-stage build:
#   Stage 1 → uses Maven + JDK to COMPILE and PACKAGE the app
#   Stage 2 → uses only JRE to RUN the app (smaller final image)
#
# FROM: Base image to start from.
# "eclipse-temurin:17-jdk-alpine" means:
#   - eclipse-temurin = a popular JDK distribution
#   - 17              = Java 17
#   - alpine          = tiny Linux OS (only ~5MB vs Ubuntu's ~72MB)
FROM eclipse-temurin:17-jdk-alpine AS builder

# WORKDIR: Sets the working directory inside the container.
# All next commands run from this directory.
# Like doing: cd /app inside the container.
WORKDIR /app

# COPY: Copies files from your computer → into the container image.
# First, copy only pom.xml to leverage Docker's layer caching.
# If pom.xml hasn't changed, Maven dependencies won't be re-downloaded.
COPY pom.xml .

# Download all Maven dependencies (cached as a separate layer).
# -B = batch mode (no interactive prompts)
# dependency:go-offline = download everything needed to build offline
RUN mvn -B dependency:go-offline

# Now copy the actual source code
COPY src ./src

# Build the application → creates a fat JAR in /app/target/
# -DskipTests = skip unit tests during Docker build (faster)
RUN mvn -B package -DskipTests


# ============================================================
# ---- STAGE 2: RUN STAGE ----
# Start fresh with a smaller image (JRE only, no Maven/JDK tools)
# This keeps the final Docker image small and secure.
# ============================================================
FROM eclipse-temurin:17-jre-alpine

# Set working directory for the runtime container
WORKDIR /app

# Copy ONLY the final JAR from the builder stage.
# We don't copy Maven, source code, or build tools — not needed at runtime.
# --from=builder = copy from Stage 1 (named "builder" above)
COPY --from=builder /app/target/spring-docker-app-1.0.0.jar app.jar

# Set an environment variable — readable inside the app via System.getenv()
# In HelloController.java, /api/info reads this to show which env is running
ENV DOCKER_ENV=Running inside Docker Container 🐳

# EXPOSE: Documents which port the app listens on.
# This does NOT publish the port — it's just metadata/documentation.
# You publish the port using: docker run -p 8080:8080
EXPOSE 8080

# ENTRYPOINT: The command that runs when the container starts.
# "java -jar app.jar" runs your Spring Boot application.
# Using array form (exec form) — preferred over shell form.
ENTRYPOINT ["java", "-jar", "app.jar"]

