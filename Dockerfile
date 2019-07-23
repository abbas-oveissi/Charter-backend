FROM openjdk:8-jdk-alpine
ADD build/libs/charter.war build/libs/charter.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/charter.war"]