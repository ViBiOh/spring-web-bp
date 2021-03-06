FROM openjdk:15-slim

HEALTHCHECK --retries=10 CMD curl --disable --silent --show-error --location --max-time 30 http://localhost:8080/actuator/health
VOLUME /tmp
ENTRYPOINT [ "java", "-Xmx128m", "-Xss256k", "-jar", "/app.jar"]

EXPOSE 8080

RUN apt-get update \
 && apt-get install -y curl \
 && apt-get autoremove -y \
 && apt-get clean all

COPY target/*.jar /app.jar
