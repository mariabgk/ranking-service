# This Dockerfile is used to deploy. Arguments have to be send via Jenkinsfile or cmd line
FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine

ARG JAR_FILE
ARG PORT=8201
ARG PROFILE=docker

ENV ENV_JAR_FILE $JAR_FILE
ENV ENV_PORT $PORT
ENV MAIN_ENV $PROFILE
ENV ENV_JAVA_OPTIONS=""
ENV ENV_SPRING_OPTIONS=""

RUN mkdir /app
ADD build/libs/${ENV_JAR_FILE} /app/${ENV_JAR_FILE}
ADD src/config/logback-spring.xml /app/logback-spring.xml
ADD src/config/application.yml /app/application.yml
ADD src/config/application-docker.yml /app/application-docker.yml

EXPOSE ${ENV_PORT}

ENTRYPOINT java ${ENV_JAVA_OPTIONS} -jar /app/${ENV_JAR_FILE} --spring.profiles.active=${MAIN_ENV} --spring.config.location=/app/ --server.port=${ENV_PORT} --logging.config=/app/logback-spring.xml ${ENV_SPRING_OPTIONS}