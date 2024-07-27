FROM openjdk:17-alpine

WORKDIR /ms-parquimetro-cadastro
CMD ["./gradlew", "clean", "bootJar"]

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8090
