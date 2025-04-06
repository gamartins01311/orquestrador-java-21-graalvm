package orquestrador.graalvm.config;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class OpenTelemetryInterceptor implements HandlerInterceptor {

    private final Tracer tracer;

    public OpenTelemetryInterceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String spanName = request.getMethod() + " " + request.getRequestURI();
        Span span = tracer.spanBuilder(spanName).startSpan();

        request.setAttribute("otelSpan", span);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Span span = (Span) request.getAttribute("otelSpan");
        if (span != null) {
            if (ex != null) {
                span.recordException(ex);
            }
            span.end();
        }
    }
}