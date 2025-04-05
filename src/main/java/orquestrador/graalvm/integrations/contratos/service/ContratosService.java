package orquestrador.graalvm.integrations.contratos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orquestrador.graalvm.controller.domains.ContratosResponse;
import orquestrador.graalvm.integrations.contratos.client.ContratosClient;

import java.util.List;

@Service
public class ContratosService {

    @Autowired
    private ContratosClient contratosClient;

    public List<ContratosResponse> buscarContratosPorCpfCnpjVirtualThread(String cpfCnpj) {
        return contratosClient.getContratosVirtualThread(cpfCnpj);
    }

    public List<ContratosResponse> buscarContratosPorCpfCnpjSemVirtualThread(String cpfCnpj) {
        return contratosClient.getContratosSemVirtualThread(cpfCnpj);
    }
}
