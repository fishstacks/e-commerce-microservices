FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/neonspecs-0.0.1-SNAPSHOT.jar /app/neonspecs-0.0.1.jar

ENTRYPOINT ["java", "-jar", "/app/neonspecs-0.0.1.jar"]
