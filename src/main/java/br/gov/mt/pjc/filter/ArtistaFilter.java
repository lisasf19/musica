package br.gov.mt.pjc.filter;

//classe criada para receber os dados do Json informado nas requisiçãos do resource /artistas 
public class ArtistaFilter {

	private String consulta;		
	private ConsultaNome tipoConsulta;

	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}	
	public ConsultaNome getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(ConsultaNome tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public boolean validaCampos() {
		if(getConsulta()==null  ||getConsulta().isEmpty() || getTipoConsulta()== null) {				
			return false;
		}else {
			return true;
		}
	}
	
}
	
	