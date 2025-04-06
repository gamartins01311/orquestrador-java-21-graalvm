package orquestrador.graalvm.config;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class OpenTelemetryConfig {

    private OpenTelemetrySdk openTelemetrySdk;

    @PostConstruct
    public void init() {
        // Força a criação em runtime
        OtlpGrpcSpanExporter exporter = OtlpGrpcSpanExporter.builder()
                .setEndpoint("http://datadog.datadog.svc.cluster.local:4317")
                .build();

        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(BatchSpanProcessor.builder(exporter).build())
                .setResource(Resource.getDefault())
                .build();

        this.openTelemetrySdk = OpenTelemetrySdk.builder()
                .setTracerProvider(sdkTracerProvider)
                .build();
    }

    @Bean
    public OpenTelemetry openTelemetry() {
        return openTelemetrySdk;
    }

    @Bean
    public Tracer tracer() {
        return openTelemetrySdk.getTracer("orquestrador");
    }
}
