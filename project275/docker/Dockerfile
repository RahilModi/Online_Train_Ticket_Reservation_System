FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/275project-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


docker-machine env
SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://localhost:3000
SET DOCKER_CERT_PATH=C:\Users\Parth\.docker\machine\machines\default
SET DOCKER_MACHINE_NAME=default
SET COMPOSE_CONVERT_WINDOWS_PATHS=true
REM Run this command to configure your shell:
REM     @FOR /f "tokens=*" %i IN ('docker-machine env') DO @%i