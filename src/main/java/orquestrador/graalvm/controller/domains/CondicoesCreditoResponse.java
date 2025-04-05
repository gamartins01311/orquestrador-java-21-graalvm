package orquestrador.graalvm.controller.domains;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class CondicoesCreditoResponse {

    private BigDecimal limiteAprovado;
    private BigDecimal taxaJurosMensal;
    private BigDecimal cet;
    private LocalDate vencimentoFatura;
    private boolean rotativoHabilitado;
}
