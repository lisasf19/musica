package br.gov.mt.pjc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.pjc.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer>{

	
}