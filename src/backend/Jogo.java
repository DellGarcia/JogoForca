package backend;

import java.util.ArrayList;
import java.util.Collections;

import data.CRUDPalavra;
import model.Palavra;

public class Jogo {

	private ArrayList<Palavra> palavras;
	
	public Jogo() {

		palavras = new ArrayList<Palavra>();
		
		for(String[] valor: CRUDPalavra.selectPalavras()) {
			palavras.add(new Palavra(valor));
		}
		Collections.shuffle(palavras);
	}

	public ArrayList<Palavra> getPalavras() {
		return palavras;
	}

	public void setPalavras(ArrayList<Palavra> palavras) {
		this.palavras = palavras;
	}
	
	
}
