package model;

public class Palavra {

	private int idPalavra;
	private String palavra;
	private String categoria;
	private String dica;
	
	public Palavra(String[] linha) {
		// TODO Auto-generated constructor stub
		idPalavra = Integer.parseInt(linha[0]);
		palavra = linha[1];
		categoria = linha[2];
		dica = linha[3];
	}

	public int getIdPalavra() {
		return idPalavra;
	}

	public void setIdPalavra(int idPalavra) {
		this.idPalavra = idPalavra;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}
	
	
}
