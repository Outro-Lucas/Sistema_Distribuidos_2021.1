package servidor;

public class Passagem {

	protected String numPassagem;	//id da Passagem
	protected String cpf;			//dados do comprador
	protected String origem;		//local de partida
	protected String destino;		//local de ida
	protected String valor;			//preco da passagem
	protected String horario;		//horario

	public Passagem() { // construtor
	}
	
	public Passagem(String numPassagem) {
		this.numPassagem = numPassagem;
	}

	public String getNumPassagem() {
		return numPassagem;
	}

	public void setNumPassagem(String numPassagem) {
		this.numPassagem = numPassagem;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "Passagem [numPassagem=" + numPassagem + ", cpf=" + cpf + ", origem=" + origem + ", destino=" + destino
				+ ", valor=" + valor + ", horario=" + horario + "]";
	}
	
}
