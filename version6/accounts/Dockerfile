FROM amazoncorretto:24-alpine3.22-jdk
LABEL maintainer=marvin@dmarv.com
WORKDIR /app
COPY target/accounts-0.0.1-SNAPSHOT.jar ./accounts-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]
