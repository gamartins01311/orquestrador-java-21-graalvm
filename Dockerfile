FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre
WORKDIR /app

# Baixar o Datadog Java Agent
ADD https://dtdg.co/latest-java-tracer /datadog/dd-java-agent.jar

COPY --from=build /app/target/*.jar app.jar

ENV JAVA_TOOL_OPTIONS="-javaagent:/datadog/dd-java-agent.jar"

ENTRYPOINT ["java", "-jar", "app.jar"]
