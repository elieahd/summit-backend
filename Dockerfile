FROM eclipse-temurin:23-jdk

COPY infrastructure/target/summit-infrastructure-*.jar summit.jar

ENTRYPOINT ["java", "-jar", "summit.jar"]
