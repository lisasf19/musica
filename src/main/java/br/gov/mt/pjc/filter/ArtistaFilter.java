package br.gov.mt.pjc.filter;

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
		if(getConsulta().isEmpty() || getConsulta()==null ) {				
			return false;
		}else {
			return true;
		}
	}
}
	
	