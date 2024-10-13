FROM amazoncorretto:23

COPY infrastructure/target/summit-infrastructure-*.jar summit.jar

ENTRYPOINT ["java", "-jar", "summit.jar"]
