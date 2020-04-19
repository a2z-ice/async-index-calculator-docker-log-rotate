FROM java:8-jdk-alpine
ADD target/index-calculator-0.0.1-SNAPSHOT.jar index-calculator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "index-calculator-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080