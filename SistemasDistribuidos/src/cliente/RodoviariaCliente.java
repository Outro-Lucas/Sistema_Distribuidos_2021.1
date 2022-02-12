package cliente;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class RodoviariaCliente {

	RodoviariaProxy proxy;

	public RodoviariaCliente() {
		proxy = new RodoviariaProxy();
	}

	public int selecionaOperacao() throws IOException, NoSuchMethodException {

		Scanner ler = new Scanner(System.in);
		int operacao;
		operacao = ler.nextInt();
		switch (operacao) {
		case 1:
			Passagem passagem = new Passagem();
			System.out.println("Numero da Passagem: ");
			passagem.setNumPassagem(ler.next());
			System.out.println("CPF do cliente: ");
			passagem.setCpf(ler.next());
			System.out.println("Local de origem: ");
			passagem.setOrigem(ler.next());
			System.out.println("Local de destino: ");
			passagem.setDestino(ler.next());
			System.out.println("Valor da passagem: ");
			passagem.setValor(ler.next());
			System.out.println("Horario de partida(hh:mm): ");
			passagem.setHorario(ler.next());
			String resultado = proxy.novaPassagem(passagem);
			System.out.println(resultado);
			break;
		case 2:
			System.out.println("Numero da passagem: ");
			Passagem resultadoConsulta = proxy.consultarPassagem(ler.next());
			if (resultadoConsulta == null) {
				System.out.println("Passagem nao encontrada! Por favor tente um numero valido.");
			} else {
				System.out.println("Informacoes da passagem:");
				System.out.println("Numero: " + resultadoConsulta.getNumPassagem() + "\nCPF: "
						+ resultadoConsulta.getCpf() + "\nLocal Origem: " + resultadoConsulta.getOrigem() 
						+ "\nLocal Destino: " + resultadoConsulta.getDestino() 
						+ "\nPreco:  R$" + resultadoConsulta.getValor() + "\nHorario de Partida: " + resultadoConsulta.getHorario());
			}

			break;
			
		case 3:
			System.out.println("Numero da Passagem: ");
			String numConta2 = ler.next();
			passagem = proxy.consultarPassagem(numConta2);
			if (passagem == null) {
				System.out.println("O numero informado nao existe!");
				break;
			}
			else {
				System.out.println("CPF do cliente: ");
				String Fcpf = ler.next();
				System.out.println("Local de origem: ");
				String Forigem = ler.next();
				System.out.println("Local de destino: ");
				String Fdestino = ler.next();
				System.out.println("Valor da passagem: ");
				String Fvalor = ler.next();
				System.out.println("Horario de partida(hh:mm): ");
				String Fhorario = ler.next();
				proxy.editarPassagem(numConta2, Fcpf, Forigem, Fdestino, Fvalor, Fhorario);
			}
			break;

		case 0:
			proxy.finaliza();
			break;

		default:
			System.out.println("Operacao invalida, tente outra.");
			break;
		}
		return operacao;
	}

	public void printMenu() {
		System.out.println("\nDigite o numero da operacao que deseja executar: ");
		System.out.println("1 - Cadastrar uma nova passagem.");
		System.out.println("2 - Consultar passagem pelo numero.");
		System.out.println("3 - Editar passagem");
		System.out.println("0 - Sair\n");
	}

	public static void main(String[] args) throws NoSuchMethodException {
		RodoviariaCliente bookClient = new RodoviariaCliente();
		int operacao = -1;
		do {
			bookClient.printMenu();
			try {
				operacao = bookClient.selecionaOperacao();
			} catch (IOException ex) {
				System.out.println("Escolha uma das operacoes pelo numero");
			}
		} while (operacao != 0);
	}
}
