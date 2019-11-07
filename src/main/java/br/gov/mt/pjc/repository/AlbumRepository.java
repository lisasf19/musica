package br.gov.mt.pjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mt.pjc.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>{
	
	@Query("select a from Album a "
			+ "where a.titulo = :titulo "
			+ "and a.artista.id = :idArtista")
	List<Album> verificarRegistroJaExistente(@Param("titulo")String titulo, @Param("idArtista")Integer id);

}
