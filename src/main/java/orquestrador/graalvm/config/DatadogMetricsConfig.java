package orquestrador.graalvm.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.datadog.DatadogConfig;
import io.micrometer.datadog.DatadogMeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.export.datadog.DatadogProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(DatadogProperties.class)
public class DatadogMetricsConfig {

    @Bean
    public DatadogMeterRegistry datadogMeterRegistry(DatadogProperties props,
                                                     @Value("${DATADOG_API_KEY}") String apiKey) {
        DatadogConfig config = new DatadogConfig() {
            @Override
            public String get(String key) {
                return null; // usa os valores padrão
            }

            @Override
            public String apiKey() {
                return apiKey;
            }

            @Override
            public String applicationKey() {
                return props.getApplicationKey();
            }

            @Override
            public String uri() {
                return props.getUri();
            }

            @Override
            public Duration step() {
                return props.getStep(); // CORRETO AGORA
            }
        };

        return new DatadogMeterRegistry(config, Clock.SYSTEM);
    }
}
