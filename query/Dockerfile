FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build target/*.jar /app/QueryService.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/QueryService.jar"]