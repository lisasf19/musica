package br.gov.mt.pjc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.pjc.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>{

}
