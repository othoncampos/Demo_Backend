package br.edu.ifba.demo.backend.api.dto;


import lombok.Getter;

@Getter
public class AutorQuantidade {
    private String autor;
    private Long quantidade;

    public AutorQuantidade(String autor, Long quantidade) {
        this.autor = autor;
        this.quantidade = quantidade;
    }
}
