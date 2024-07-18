FROM amazoncorretto:21-alpine-jdk

COPY target/demo-project-0.0.1-SNAPSHOT.jar demo-project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/demo-project-0.0.1-SNAPSHOT.jar"]