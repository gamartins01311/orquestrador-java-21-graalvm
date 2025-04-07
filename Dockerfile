FROM debian:bookworm-slim as graalvm-native

RUN apt-get update && apt-get install -y zlib1g && apt-get clean

COPY docker-layer/orquestrador.java.21 /app/orquestrador.java.21
ENTRYPOINT ["/app/orquestrador.java.21"]
