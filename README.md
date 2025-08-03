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

### Build & Run
```bash
mvn clean package
mvn spring-boot:run
```

### Usage
```bash
curl "http://localhost:8080/api/normalize?title=Chief%20Accountant"
# Response: {"normalizedTitle":"Accountant"}
```

### Configuration
Edit `src/main/resources/application.yml`:
```yaml
normalizer:
  titles:
    - Architect
    - Software engineer
    - Quantity surveyor
    - Accountant
  threshold: 0.5

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

## Actuator Endpoints
- `GET /actuator/health`  
- `GET /actuator/metrics`  
- `GET /actuator/info`  

## API Documentation
Once running, you can explore and test the REST API via Swagger UI:

- **Swagger UI**:  
  http://localhost:8080/swagger-ui.html  
  (or http://localhost:8080/swagger-ui/index.html)

- **OpenAPI JSON**:  
  http://localhost:8080/v3/api-docs

## Project Structure
```
job-title-normalizer-spring-prod/
├── README.md
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/ethan/normalizer/
│   │   │   ├── JobTitleNormalizerApplication.java
│   │   │   ├── config/NormalizerProperties.java
│   │   │   ├── config/OpenApiConfig.java
│   │   │   ├── service/
│   │   │   │   ├── NormalisationService.java
│   │   │   │   ├── SimpleTokenMatcher.java
│   │   │   │   └── JobTitleNormaliserService.java
│   │   │   ├── controller/
│   │   │   │   ├── NormalisationController.java
│   │   │   │   └── NormalisationResponse.java
│   │   │   └── exception/GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/ethan/normalizer/
│           ├── MatchResultTest.java
│           ├── service/
│           │   ├── JobTitleNormaliserServiceTest.java
│           │   ├── SimpleTokenMatcherTest.java
│           │   ├── MatcherThresholdTest.java
│           │   └── ServiceExtraEdgeCasesTest.java
│           └── controller/
│               ├── NormalisationControllerTest.java
│               └── ControllerExtraTest.java
```

## License
MIT License