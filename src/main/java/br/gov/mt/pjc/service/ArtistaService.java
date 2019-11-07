package br.gov.mt.pjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	public Artista buscarPorId(Integer id) {
		Optional<Artista> artistaOptional = artistaRepository.findById(id);
		//verifica se foi encontrado registro
		if(artistaOptional.isPresent()) {
			return artistaOptional.get();
		}else {
			throw new RuntimeException("Artista não cadastrado. Código não encontrado.");
		}		
	}
	
	public Artista alterarArtista(Integer id, Artista artista) {
		//verifica se o registro a ser alterado já existe no banco de dados
		Artista artistaSalvo = this.buscarPorId(id);
		
		if(artistaSalvo != null) {
			BeanUtils.copyProperties(artista, artistaSalvo, "id");
			return artistaRepository.save(artistaSalvo);
		}else {
			throw new RuntimeException("Artista não encontrado.");
		}
	}

}
