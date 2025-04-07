FROM busybox:glibc as graalvm-native
COPY docker-layer/orquestrador /app/orquestrador
COPY /home/jenkins-agent/dd-java-agent.jar /app/dd-java-agent.jar
ENTRYPOINT ["/app/orquestrador"]
