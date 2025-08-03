FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml ./

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /workspace/target/job-title-normalizer.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
