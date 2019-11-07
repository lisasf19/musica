package br.gov.mt.pjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.mt.pjc.model.Album;
import br.gov.mt.pjc.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;


	public List<Album> buscarTodos(){
		return albumRepository.findAll();
	}
	
	public void apagarArtista(Integer id) {
		albumRepository.deleteById(id);
	}


}
