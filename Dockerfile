
#Stage 1
# initialize build and set base image for first stage
FROM maven:3.8.6-eclipse-temurin-19-focal as start
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# set working directory
WORKDIR /parking
# copy just pom.xml
COPY pom.xml .
# go-offline using the pom.xml
RUN mvn dependency:go-offline
# copy your other files
COPY ./src ./src
# compile the source code and package it in a jar file
RUN mvn clean install -Dmaven.test.skip=true
#Stage 2
# set base image for second stage
FROM eclipse-temurin:19-jre
# set deployment directory
WORKDIR /parking
# copy over the built artifact from the maven image
COPY --from=start /parking/target/app.jar /parking
ENTRYPOINT ["java","-jar","/app.jar"]

