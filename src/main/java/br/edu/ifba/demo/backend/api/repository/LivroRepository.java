package br.edu.ifba.demo.backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifba.demo.backend.api.dto.AnoQuantidade;
import br.edu.ifba.demo.backend.api.model.LivroModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long>
{
	Optional<LivroModel> findByIsbn(String isbn);
    Optional<LivroModel> findByTitulo(String titulo);

	@Query(value = "SELECT CONVERT(l.ano_publicacao, SIGNED) AS ano_publicacao, COUNT(*) FROM livro l GROUP BY l.ano_publicacao order by l.ano_publicacao;", nativeQuery = true)
	List<Object[]> getAnoQuant();
}
