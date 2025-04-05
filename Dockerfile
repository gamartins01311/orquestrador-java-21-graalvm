FROM busybox:glibc
WORKDIR /app
COPY target/orquestrador /app/orquestrador
RUN chmod +x /app/orquestrador
ENTRYPOINT ["/app/orquestrador"]
