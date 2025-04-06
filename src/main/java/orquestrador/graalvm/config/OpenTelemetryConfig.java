package orquestrador.graalvm.config;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OpenTelemetry openTelemetry() {
        var exporter = OtlpGrpcSpanExporter.builder()
                .setEndpoint("http://datadog.datadog.svc.cluster.local:4317")
                .build();

        var resource = Resource.getDefault().merge(
                Resource.create(io.opentelemetry.api.common.Attributes.of(
                        ResourceAttributes.SERVICE_NAME, "orquestrador-graalvm",
                        ResourceAttributes.SERVICE_VERSION, "1.0.9"
                ))
        );

        var tracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(BatchSpanProcessor.builder(exporter).build())
                .setResource(resource)
                .build();

        var sdk = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .build();

        return sdk;
    }

    @Bean
    public Tracer tracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer("orquestrador.graalvm");
    }
}