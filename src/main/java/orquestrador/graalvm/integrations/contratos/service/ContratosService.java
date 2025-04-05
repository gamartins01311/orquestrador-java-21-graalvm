package orquestrador.graalvm.integrations.contratos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orquestrador.graalvm.controller.domains.ContratosResponse;
import orquestrador.graalvm.integrations.contratos.client.ContratosClient;

@Service
public class ContratosService {

    @Autowired
    private ContratosClient contratosClient;

    public ContratosResponse buscarContratosPorCpfCnpjVirtualThread(String cpfCnpj) {
        return contratosClient.getContratosVirtualThread(cpfCnpj);
    }

    public ContratosResponse buscarContratosPorCpfCnpjSemVirtualThread(String cpfCnpj) {
        return contratosClient.getContratosSemVirtualThread(cpfCnpj);
    }
}
