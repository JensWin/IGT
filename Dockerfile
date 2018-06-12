FROM openjdk:8
ADD target/IGT-Server.jar IGT-Server.jar
EXPOSE 9005
ENTRYPOINT ["java", "-jar", "IGT-Server.jar"]