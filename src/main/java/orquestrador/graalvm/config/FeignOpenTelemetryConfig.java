package orquestrador.graalvm.config;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapSetter;
import io.opentelemetry.api.GlobalOpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignOpenTelemetryConfig {

    @Bean
    public RequestInterceptor otelFeignRequestInterceptor() {
        return new RequestInterceptor() {

            private final TextMapSetter<RequestTemplate> setter = RequestTemplate::header;

            @Override
            public void apply(RequestTemplate template) {
                Context context = Context.current();
                GlobalOpenTelemetry.getPropagators()
                        .getTextMapPropagator()
                        .inject(context, template, setter);
            }
        };
    }
}
