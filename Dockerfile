FROM openjdk:11
EXPOSE 8888
COPY target/HumanResourceApp.jar HumanResourceApp.jar
ENTRYPOINT ["java","-jar","/HumanResourceApp.jar"]

