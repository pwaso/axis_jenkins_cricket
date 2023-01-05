
FROM openjdk:8

ADD /target/springbootdemo-0.0.1-SNAPSHOT.jar cricketapp.jar
#Expose 8080 port for hitting REST end points
EXPOSE 8080

ENTRYPOINT [ "java","-jar","cricketapp.jar" ]