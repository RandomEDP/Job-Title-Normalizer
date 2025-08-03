# Job Title Normalizer Spring Boot Service

## Overview
A production-ready Spring Boot microservice to normalize job titles against a configurable set of ideal titles using a token-based matching strategy.

## Features
- **Configuration**: Externalized in `application.yml` (titles list & similarity threshold)  
- **Dependency Injection**: Fully uses Spring's IoC for services and components  
- **Validation**: Method-level and request parameter validation with global exception handling  
- **Observability**: Spring Boot Actuator endpoints, Micrometer metrics, structured SLF4J logging  
- **REST API**: `GET /api/normalize?title=...` returns JSON `{ "normalizedTitle": "..." }`  
- **API Documentation**: Interactive Swagger UI for exploring and testing  
- **Tests**: Comprehensive unit, parameterized, edge-case, and integration tests  

## Getting Started

### Prerequisites
- Java 17+  
- Maven 3.8+  

## 📦 Build & Run Locally

1. Update titles & threshold in `application.yml` if needed.  
2. Run the application directly with Maven:
   ```bash
   mvn spring-boot:run
   ```
3. Open Swagger UI at `http://localhost:8080/swagger-ui.html`.

## 🚀 Live Swagger UI

Browse and “Try it out” here:  
https://job-title-normalizer-1083685539637.europe-west1.run.app/swagger-ui/index.html

## ☁️ CI/CD & Deployment

- **cloudbuild.yaml**: automates build, Docker push, and deploy to Cloud Run.  
- **Cloud Run service:** `job-title-normalizer` in `europe-west1`.  
- **Environment variables** in Cloud Run:
  - `APP_API_BASE_URL=https://job-title-normalizer-1083685539637.europe-west1.run.app`

---

## 🔧 Project Structure

```
job-title-normalizer-spring-prod2/
├── .dockerignore
├── .gitignore
├── app.yaml
├── cloudbuild.yaml
├── Dockerfile
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/ethan/normalizer/
    │   │   ├── JobTitleNormalizerApplication.java
    │   │   ├── MatchResult.java
    │   │   ├── TitleMatcher.java
    │   │   ├── config/
    │   │   │   ├── NormalizerProperties.java
    │   │   │   ├── OpenApiConfig.java
    │   │   │   └── WebConfig.java
    │   │   ├── controller/
    │   │   │   ├── NormalisationController.java
    │   │   │   └── NormalisationResponse.java
    │   │   ├── exception/
    │   │   │   └── GlobalExceptionHandler.java
    │   │   └── service/
    │   │       ├── NormalisationService.java
    │   │       ├── JobTitleNormaliserService.java
    │   │       └── SimpleTokenMatcher.java
    │   └── resources/
    │       └── application.yml
    └── test/
        └── java/com/ethan/normalizer/
            ├── controller/
            │   ├── ControllerExtraTest.java
            │   └── NormalisationControllerTest.java
            ├── matchResult/
            │   └── MatchResultTest.java
            └── service/
                ├── JobTitleNormaliserServiceTest.java
                ├── MatcherThresholdTest.java
                ├── ServiceExtraEdgeCasesTest.java
                └── SimpleTokenMatcherTest.java
```

## ⚙️ Configuration (`application.yml`)

```yaml
normalizer:
  titles:
    - Architect
    - Software engineer
    - Quantity surveyor
    - Accountant
    - Builder
  threshold: 0.5

app:
  api:
    base-url: ${APP_API_BASE_URL:}

springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    url:  /v3/api-docs
    config-url: /v3/api-docs/swagger-config

management:
  endpoints:
    web:
      exposure: [health,info,metrics]
```

> **Tip:** To extend or refine matching, you can **add or remove** entries in the `normalizer.titles` list and **tune** the `normalizer.threshold` value in this file before starting the app.

_Thanks for using the Job Title Normalizer!_
