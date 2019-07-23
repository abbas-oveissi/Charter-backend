FROM openjdk:8-jdk-alpine
ADD build/libs/Charter-backend.war build/libs/Charter-backend.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/Charter-backend.war"]