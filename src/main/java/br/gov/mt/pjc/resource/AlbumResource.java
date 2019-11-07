package br.gov.mt.pjc.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.pjc.model.Album;
import br.gov.mt.pjc.service.AlbumService;

@RestController
@RequestMapping("/albuns")
public class AlbumResource {

	@Autowired
	private AlbumService albumService;
	
	@GetMapping
	public List<Album> listarAlbuns(){
		return albumService.buscarTodos();
	}
	
	@PostMapping
	public Album cadastrarAlbum(@Valid @RequestBody Album album) {
		return albumService.salvar(album);
	}
	
	@PutMapping("/{id}")
	public Album alterarAlbum(@Valid @RequestBody Album album,
			@PathVariable("id") Integer id) {
		return albumService.alterarAlbum(id, album);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarAlbum(@PathVariable Integer id){
		try {
			albumService.apagarAlbum(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Album removido com sucesso.");
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Album informado não encontrado. \nErro de origem:"+ e.getMessage());
		}
	}
}
