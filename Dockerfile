FROM openjdk:11.0-jdk-slim-stretch
ARG DEPENDENCY=target
COPY ${DEPENDENCY}/app-tra-documents-service-api-1.0.0-SNAPSHOT.jar /home/app-tra-documents-service-api-1.0.0-SNAPSHOT.jar
RUN echo $ENVIROMENTS
EXPOSE 80
ENTRYPOINT   [ "java","-jar","-Dspring.profiles.active=release","/home/app-tra-documents-service-api-1.0.0-SNAPSHOT.jar" ] 
