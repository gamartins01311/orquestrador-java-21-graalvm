FROM busybox:glibc

WORKDIR /app

COPY target/*-runner /app/orquestrador
RUN chmod +x /app/orquestrador

ENTRYPOINT ["/app/orquestrador"]
