package br.gov.mt.pjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.mt.pjc.model.Artista;
import br.gov.mt.pjc.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
		
	public List<Artista> buscarTodos(){
		return artistaRepository.findAll();
	}
	
	public void apagarArtista(Integer id) {
		artistaRepository.deleteById(id);
	}

}
