FROM openjdk:16-jdk-slim

WORKDIR /app

COPY target/charity-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
