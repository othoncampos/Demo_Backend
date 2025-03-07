package br.edu.ifba.demo.backend.api.controller;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.demo.backend.api.repository.LivroRepository;
import br.edu.ifba.demo.backend.api.dto.AnoQuantidade;
import br.edu.ifba.demo.backend.api.dto.AutorQuantidade;
import br.edu.ifba.demo.backend.api.model.LivroModel;

@RestController
@RequestMapping("/livro")
public class LivroController {
	
	private LivroRepository livroRepository;

	public LivroController(LivroRepository repository) {
		super();
		this.livroRepository = repository;
	}

	@GetMapping
	public String teste() {
		return "Testando Rota Usuario";
	}
	
	// Método que retornar todos os usuarios do banco de dados
	@GetMapping("/listall")
	public List<LivroModel> listall() {
		var livros = livroRepository.findAll();
		return livros;
	}

    @GetMapping("/count")
	public Long count() {
		var count = livroRepository.count();
		return count;
	}

    @GetMapping("/getById/{id}")
    public LivroModel getById(@PathVariable("id") String id) {
        Optional<LivroModel> livro = livroRepository.findById(Long.parseLong(id));
        if (livro.isPresent())
            return livro.get();
        return null;
    }

    @GetMapping("/getByIsbn/{isbn}")
    public LivroModel getByIsbn(@PathVariable("isbn") String isbn) {
        Optional<LivroModel> livro = livroRepository.findByIsbn(isbn);
        if (livro.isPresent())
            return livro.get();
        return null;
    }
    
    @GetMapping("/getByTitulo/{titulo}")
    public LivroModel getbyTitulo(@PathVariable("titulo") String titulo) {
        Optional<LivroModel> livro = livroRepository.findByTitulo(titulo);
        if (livro.isPresent())
            return livro.get();
        return null;
    }

    @PostMapping
    public ResponseEntity<LivroModel> addLivro(@RequestBody LivroModel livro) {
        LivroModel savedLivro = livroRepository.save(livro);
        return new ResponseEntity<LivroModel>(savedLivro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroModel> deleteLivro(@PathVariable("id") Long id) {
        Optional<LivroModel> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.delete(livro.get());
            return new ResponseEntity<LivroModel>(livro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAnoQuant/")
    public List<AnoQuantidade> getLivroCount() {
        List<Object[]> resultados = livroRepository.getAnoQuant();

        System.out.println("getAnoQuantidade: 2" + resultados.size());
        for (Object[] object : resultados) {
            System.out.println(object[0].toString() + " : " + object[1].toString());
        }
        
        List<AnoQuantidade> anoquant = resultados.stream()
        .filter(r -> r != null && r.length >= 2 && r[0] != null && r[1] != null)
        .map(r -> new AnoQuantidade(
            Integer.parseInt(r[0].toString()), 
            ((Number) r[1]).longValue()
        ))
        .collect(Collectors.toList());

        return anoquant;
    }

    @GetMapping("/getAutorQuant/")
    public List<AutorQuantidade> getAutorQuant(){
        System.out.println("getAutorQuant: 1");
        List<Object[]> resultados = livroRepository.getAutorQuant();
        System.out.println("getAutorQuant: 2" + resultados.size());
        for (Object[] object : resultados) {
            System.out.println(object[0].toString() + " : " + object[1].toString());
        }

        List<AutorQuantidade> autorquant = resultados.stream()
        .filter(r -> r != null && r.length >= 2 && r[0] != null && r[1] != null)
        .map(r -> new AutorQuantidade(
            r[0].toString(), 
            ((Number) r[1]).longValue()
        ))
        .collect(Collectors.toList());

        return autorquant;
    }
    
}