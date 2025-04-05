package orquestrador.graalvm.controller.domains;

import lombok.Getter;

@Getter
public class ClienteResponse {

    private String nome;
    private String cpfCnpj;
    private String endereço;
    private String estadoCivil;
    private String profissao;
}
