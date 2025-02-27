package br.edu.ifba.demo.backend.api.model;

import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "livro")
public class LivroModel {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
    private Long id_livro;
    
    @Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "autor", nullable = false)
    private String autor;
    
    @Column(name = "editora", nullable = true)
    private String editora;
    
    @Column(name = "ano_publicacao", nullable = true)
    private Integer ano_publicacao;

    @Column(name = "genero", nullable = true)
    private String genero;

    @Column(name = "isbn", nullable = true)
    private String isbn;

    @Column(name = "num_paginas", nullable = true)
    private Integer num_paginas;

    @Column(name = "sinopse", nullable = true)
    private String sinopse;

    @Column(name = "idioma", nullable = true)
    private String idioma;

    @Column(name = "data_cadastro", nullable = true)
    private LocalDateTime data_cadastro;

    @Column(name = "preco", nullable = true)
    private Double preco;
}
