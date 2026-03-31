FROM eclipse-temurin:17-jdk
ARG JAR_FILE=build/libs/demo-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]