FROM openjdk:17-jdk-slim-buster
COPY build/libs/pointer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "bash", "entrypoint.prod.sh"]