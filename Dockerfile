FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/blueberry-0.0.1-SNAPSHOT.jar blueberry.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "blueberry.jar"]