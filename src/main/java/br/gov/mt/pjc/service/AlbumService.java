package br.gov.mt.pjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.mt.pjc.model.Album;
import br.gov.mt.pjc.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	private List<Album> listaAlbum;
	
	//Retorna todos os registros de Album cadastrados
	public List<Album> buscarTodos(){
		return albumRepository.findAll();
	}	
	
	public Album buscarPorId(Integer id) {
		Optional<Album> albumOptional = albumRepository.findById(id);
		//verifica se foi encontrado registro
		if(albumOptional.isPresent()) {
			return albumOptional.get();
		}else {
			throw new RuntimeException("Album não cadastrado. Código não encontrado.");
		}		
	}
	
	public Album alterarAlbum(Integer id, Album album) {
		//verifica se o registro a ser alterado já existe no banco de dados
		Album albumSalvo = this.buscarPorId(id);
		
		if(albumSalvo != null) {
			BeanUtils.copyProperties(album, albumSalvo, "id");
			return albumRepository.save(albumSalvo);
		}else {
			throw new RuntimeException("Album não encontrado.");
		}
	}
	
	public void apagarAlbum(Integer id) {
		albumRepository.deleteById(id);
	}
	
	public Album salvar(Album album) {
		//verifica campos obrigatórios
		if(album.verificaCamposObrigatorios()) {
			//verifica se há um registro de album com as mesmas informações já cadastradas
			listaAlbum = albumRepository.verificarRegistroJaExistente(album.getTitulo(), album.getArtista().getId());
			
			//se não houver registro já cadastrado realiza a operação
			if(listaAlbum== null || listaAlbum.isEmpty()) {
				return albumRepository.save(album);
			}else {
				throw new RuntimeException("Album já cadastrado.");
			}			
		}else {
			throw new RuntimeException("Dados inválidos! Não foi possível persistir o registro.");
		}
	}	

}

