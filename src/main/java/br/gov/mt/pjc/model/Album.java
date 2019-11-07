package br.gov.mt.pjc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name= "album")
public class Album implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@ManyToOne()
	@JoinColumns(@JoinColumn(name = "id_artista", referencedColumnName = "id"))
	private Artista artista;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Album(Integer id, String titulo, Artista artista) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
	}

	public Album() {
		super();
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", titulo=" + titulo + ", artista=" + artista + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean verificaCamposObrigatorios() {
		if(getTitulo() == null || getTitulo().isEmpty() || 
				getArtista().getId() == null || getArtista().getId()== 0 ) {
			return false;
		}else {
			return true;
		}
	}
		
}
