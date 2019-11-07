package br.gov.mt.pjc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
