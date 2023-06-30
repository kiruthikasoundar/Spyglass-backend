FROM openjdk:8-jdk
WORKDIR /app
COPY target/Spyglass-backend-0.0.1-SNAPSHOT.jar spyglass-docker.jar
COPY /honeycomb-opentelemetry-javaagent-1.5.0.jar honeycomb.jar
ENV SERVICE_NAME=spyglass-service
ENV HONEYCOMB_API_KEY=IfgnJ2BvtMpercLyLuh5HJ
ENV HONEYCOMB_METRICS_DATASET=spyglass-test-metrics
EXPOSE 8080
CMD ["java", "-javaagent:honeycomb.jar", "-jar", "spyglass-docker.jar"]