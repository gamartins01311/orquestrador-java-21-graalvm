package orquestrador.graalvm.controller.domains;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class ContratosResponse {

    private String nomeContrato;
    private String numeroProduto;
    private String codigoProduto;
    private CondicoesCreditoResponse condicoes;
    private LocalDate dataInicioContrato;
    private LocalDate dataTerminoContrato;
    private BigDecimal multaAtraso;
    private BigDecimal jurosMora;
    private String politicaCancelamento;
    private List<ClausulaResponse> clausulasEspeciais;
    private ClienteResponse cliente;
}
