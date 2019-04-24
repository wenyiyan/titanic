FROM openjdk:8
ADD target/titanic-0.0.1-SNAPSHOT.jar titanic-0.0.1-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","titanic-0.0.1-SNAPSHOT.jar"]