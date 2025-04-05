package orquestrador.graalvm.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import orquestrador.graalvm.controller.domains.ContratosResponse;
import orquestrador.graalvm.integrations.contratos.service.ContratosService;

import java.util.concurrent.Executors;

@Slf4j
@Component
public class ContratosFacade {

    @Autowired
    private ContratosService contratoService;

    public ContratosResponse getAllContratosByCpfCnpjVirtualThread(final String cpfCnpj) throws Exception {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start("VirtualThreadCall");

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            return executor.submit(() -> contratoService.buscarContratosPorCpfCnpjVirtualThread(cpfCnpj)).get();
        } finally {
            stopwatch.stop();
            log.info("⏱️ Tempo de execução (Virtual Thread): {}ms", stopwatch.getTotalTimeMillis());
        }
    }


    public ContratosResponse getAllContratosByCpfCnpjSemVirtualThread(final String cpfCnpj) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start("SemVirtualThreadCall");

        try {
            return contratoService.buscarContratosPorCpfCnpjSemVirtualThread(cpfCnpj);
        } finally {
            stopwatch.stop();
            log.info("⏱️ Tempo de execução (Thread tradicional): {}ms", stopwatch.getTotalTimeMillis());
        }
    }


}
