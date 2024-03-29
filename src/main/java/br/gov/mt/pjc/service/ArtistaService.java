package br.gov.mt.pjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.mt.pjc.filter.ArtistaFilter;
import br.gov.mt.pjc.filter.ConsultaNome;
import br.gov.mt.pjc.model.Artista;
import br.gov.mt.pjc.repository.ArtistaRepository;


@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	private List<Artista> listaArtista;		
	
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
			//retorna o registro encontrado
			return artistaOptional.get();
		}else {
			throw new RuntimeException("Artista não cadastrado. Código não encontrado.");
		}		
	}
	
	public Artista alterarArtista(Integer id, Artista artista) {
		//verifica se o registro a ser alterado já existe no banco de dados
		Artista artistaSalvo = this.buscarPorId(id);
		//caso existe persiste as informaç
		if(artistaSalvo != null) {
			//é realizada uma copia de artista para a istancia de artistaSalvo
			BeanUtils.copyProperties(artista, artistaSalvo, "id");
			//atualiza o registro
			return artistaRepository.save(artistaSalvo);
		}else {
			throw new RuntimeException("Artista não encontrado.");
		}
	}
	
	public Artista salvar(Artista artista) {
		//verifica campos obrigatórios
		if(artista.verificaCamposObrigatorios()) {
			//verifica se há um registro de artista com as mesmas informações já cadastradas
			listaArtista = artistaRepository.verificarRegistroJaExistente(artista.getNome());
			
			//se não houver registro já cadastrado realiza a operação
			if(listaArtista == null || listaArtista.isEmpty()) {
				return artistaRepository.save(artista);
			}else {
				throw new RuntimeException("Artista já cadastrado.");
			}			
		}else {
			throw new RuntimeException("Dado inválido! Não foi possível persistir o registro.");
		}
	}
	
	//método para pesquisar artista por nome considerando o tipo da consulta informada
	public List<Artista> buscarPorNome(ArtistaFilter filtro){
		//valida campos obrigatórios
		if(filtro.validaCampos()) {	
			//verifica o tipo de pesquisa informado e aciona o método de consulta correspondente
			if(filtro.getTipoConsulta().equals(ConsultaNome.DESC)) {
				return artistaRepository.buscarPorNomeDesc(filtro.getConsulta());
				
			}else if(filtro.getTipoConsulta().equals(ConsultaNome.ASC))  {
				return artistaRepository.buscarPorNomeAsc(filtro.getConsulta());
				
			}else {
				//converte o parâmetro para Long por causa da função char_length que será usada na consulta
				return artistaRepository.buscarPorNomeQtdLetras(Long.valueOf(filtro.getConsulta()));
			}
		}else {
			throw new RuntimeException("Dados inválidos!");
		}		
	}
	

}
