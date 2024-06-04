FROM openjdk:21

ARG APP_JAR=*.jar

WORKDIR /app
COPY ${APP_JAR} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8086
