FROM eclipse-temurin:17
EXPOSE 8081
ADD target/*.jar docker-compose-springboot.jar 
ENTRYPOINT ["java","-jar","/docker-compose-springboot.jar"]

