FROM amazoncorretto:17
ARG JAR_FILE=puzzle-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENV DB_HOST host.docker.internal
ENV DB_PORT 13307
ENV DB_CATALOG puzzle
ENV DB_PASSWORD 1234
ENV DB_USERNAME root
