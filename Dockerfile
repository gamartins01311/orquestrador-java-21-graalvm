FROM busybox:glibc as graalvm-native
COPY target/orquestrador.java.21 /app/orquestrador
ENTRYPOINT ["/app/orquestrador"]
