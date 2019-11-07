package br.gov.mt.pjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mt.pjc.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer>{

		@Query("select a from Artista "
			+ "where a.nome = :nome")
	List<Artista> verificarRegistroJaExistente( @Param("nome") String nome);

	
}