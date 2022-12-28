

FROM maven:3.8.6-eclipse-temurin-19-focal as stage1

COPY ./ /src

RUN mvn -f /src/pom.xml clean package

FROM eclipse-temurin:19-jre
# set deployment directory
WORKDIR /parking

COPY --from=stage1 /src/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

