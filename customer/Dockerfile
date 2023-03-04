ARG JDK_VERSION=19-oraclelinux8

FROM openjdk:${JDK_VERSION}
LABEL maintainer="Dimas Maryanto <software.dimas_m@icloud.com>"

ARG JAR_FILE="customer-api.jar"
# copy file from local to images then rename to spring-boot.jar
ADD target/$JAR_FILE spring-boot.jar

ENV APPLICATION_PORT=9090
ENV PROFILE=default
ENV DATABASE_USER=test
ENV DATABASE_PASSWORD=test
ENV DATABASE_HOST=localhost
ENV DATABASE_NAME=test
ENV DATABASE_PORT=3306
ENV FLYWAY_ENABLED=true

# reqired command to run application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "spring-boot.jar"]