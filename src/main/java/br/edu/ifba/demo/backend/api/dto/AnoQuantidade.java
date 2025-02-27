package br.edu.ifba.demo.backend.api.dto;


import lombok.Getter;

@Getter
public class AnoQuantidade {
    private Integer ano_publicacao;
    private Long quantidade;

    public AnoQuantidade(Integer anoPublicacao, Long quantidade) {
        this.ano_publicacao = anoPublicacao;
        this.quantidade = quantidade;
    }


}
