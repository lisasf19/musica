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

import br.gov.mt.pjc.filter.ArtistaFilter;
import br.gov.mt.pjc.model.Artista;
import br.gov.mt.pjc.service.ArtistaService;

@RestController
@RequestMapping("/artistas")
public class ArtistaResource {
	
	@Autowired
	private ArtistaService artistaService;
	
	@GetMapping
	public List<Artista> listarArtistas(){
		return artistaService.buscarTodos();
	}
	
	@PostMapping
	public Artista cadastrarArtista(@Valid @RequestBody Artista artista) {
		return artistaService.salvar(artista);
	}
	
	@PutMapping("/{id}")
	public Artista alterarArtista(@Valid @RequestBody Artista artista,
			@PathVariable("id") Integer id) {
		return artistaService.alterarArtista(id, artista);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarArtista(@PathVariable Integer id){
		try {
			artistaService.apagarArtista(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Artista removido com sucesso.");
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Artista informado não encontrado. \nErro de origem:"+ e.getMessage());
		}
	}
	
	@GetMapping("/nome-ordena")
	public List<Artista> buscarPorNomeOrdenação(@Valid @RequestBody ArtistaFilter filtro) {
		return artistaService.buscarPorNomeOrdenacao(filtro);
	}

}
