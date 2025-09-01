FROM amazoncorretto:24-alpine3.22-jdk
LABEL maintainer=marvin@dmarv.com
WORKDIR /app
COPY target/cards-0.0.1-SNAPSHOT.jar ./cards-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","cards-0.0.1-SNAPSHOT.jar"]
