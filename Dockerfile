# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="fabriociooak@gmail.com"

# Add a volume pointing to /tmp
VOLUME /home/nicky/Documentos/build

# Make port 8443 available to the world outside this container
EXPOSE 8443

# The application's jar file
ARG JAR_FILE=target/proxy-reverse-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} proxy-reverse-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/proxy-reverse-0.0.1-SNAPSHOT.jar"]