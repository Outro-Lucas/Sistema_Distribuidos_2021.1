package servidor;

import java.util.ArrayList;
import java.util.Iterator;


public class RodoviariaServente {

	// Essa eh a classe que REALMENTE implementa o servico.
	static ArrayList<Passagem> passagens = new ArrayList<Passagem>();
	
	public String novaPassagem(Passagem passagem) {
		passagens.add(passagem);
		return "Cadastrada com sucesso!";
	}
	
	public Passagem consultarPassagem(String numPassagem) {
		
		for (Passagem passagem : passagens) {
			
			if(passagem.getNumPassagem().equals(numPassagem)) {
				return passagem;
			}
		}
		return null;
	}

	public String editarPassagem(Passagem passagem) {
		
		for (Passagem pass : passagens) {
			
			if(pass.getNumPassagem().equals(passagem.getNumPassagem())) {
				pass.setCpf(passagem.getCpf());
				pass.setOrigem(passagem.getOrigem());
				pass.setDestino(passagem.getDestino());
				pass.setValor(passagem.getValor());
				pass.setHorario(passagem.getHorario());
				
				return pass.toString(); 
			}
		}
		
		return null;
	}
	
}