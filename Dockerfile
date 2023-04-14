FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/Projeto-Spring-0.0.1-SNAPSHOT.jar Projeto-Spring-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Projeto-Spring-0.0.1-SNAPSHOT.jar"]