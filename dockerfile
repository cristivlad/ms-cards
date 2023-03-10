FROM openjdk:17-oracle
COPY target/*.jar ms-cards.jar
ENTRYPOINT ["java", "-jar", "/ms-cards.jar"]
EXPOSE 8082