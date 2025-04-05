package orquestrador.graalvm.integrations.contratos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import orquestrador.graalvm.controller.domains.ContratosResponse;

@FeignClient(name = "contratosClient", url = "${contratos-client.url}")
public interface ContratosClient {
    @GetMapping("/api/contratos/vt/{cpfCnpj}")
    ContratosResponse getContratosVirtualThread(@PathVariable("cpfCnpj") String cpfCnpj);

    @GetMapping("/api/contratos/novt/{cpfCnpj}")
    ContratosResponse getContratosSemVirtualThread(@PathVariable("cpfCnpj") String cpfCnpj);
}
