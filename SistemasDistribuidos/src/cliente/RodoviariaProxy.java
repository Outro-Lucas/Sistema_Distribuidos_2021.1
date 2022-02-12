package cliente;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import servidor.Mensagem;

public class RodoviariaProxy {
	int contMessageId = 0;
	int requestiId = 0;
	Gson gson = new Gson();
	UDPClient udpClient = new UDPClient("localhost", 7896);
	
	public String novaPassagem(Passagem passagem) throws IOException {
		// (1) Empacota argumentos de entrada
		String msg = gson.toJson(passagem); // gera um json do objeto Passagem
		byte[] msgEmpac = msg.toString().getBytes("utf-8"); // transforma a string do json em um byteArray
		//(2) Chama doOperation
		byte resposta[] = doOperation("Passagem", "novaPassagem", msgEmpac);
		//(3) Desempacota argumento de resposta (retorno de doOperation)
		String respostaJson = new String(resposta, java.nio.charset.StandardCharsets.UTF_8);
		//(4) Retorna reposta desempacotada
		Object obj = new Object();
		obj = gson.fromJson(respostaJson, obj.getClass());
		return obj.toString();
	}

	public Passagem consultarPassagem(String numPassagem) throws IOException {
		String msg = gson.toJson(numPassagem);
		byte[] msgEmpac = msg.toString().getBytes("utf-8");
		byte[] resposta = doOperation("Passagem", "consultarPassagem", msgEmpac);
		String respostaJson = new String(resposta, java.nio.charset.StandardCharsets.UTF_8);
		// (4) Retorna resposta desempacotada
		Object obj = new Object();
		obj = gson.fromJson(respostaJson, obj.getClass());
		if (obj == null) {
			return null;
		}
		Passagem passagem = gson.fromJson(respostaJson, Passagem.class);
		return passagem;
	}

    public String editarPassagem(String numPassagem, String cpf, String origem, String destino, String valor, String horario) throws IOException{
		Passagem passagem = new Passagem();
		passagem.setNumPassagem(numPassagem);
		passagem.setCpf(cpf);
		passagem.setOrigem(origem);
		passagem.setDestino(destino);
		passagem.setValor(valor);
		passagem.setHorario(horario);
		
		String msgJson = gson.toJson(passagem);
		byte[] msgEmpac = msgJson.toString().getBytes("utf-8");
		byte[] resposta = doOperation("Passagem", "editarPassagem", msgEmpac);
		String respostaJson = new String(resposta, java.nio.charset.StandardCharsets.UTF_8);
		// (4) Retorna reposta desempacotada
		Object obj = new Object();
		obj = gson.fromJson(respostaJson, obj.getClass());
		if (obj == null) {
			return null;
		}
		return obj.toString();
    }

	public byte[] doOperation(String objectRef, String method, byte[] args) throws IOException {

		byte[] data = empacotaMensagem(objectRef, method, args);
		byte[] respostaCompac = null;
		Mensagem resposta = null;
		boolean flagContole = true;
		
		while(flagContole) {
			// envio
			udpClient.sendRequest(data);
			// recebimento
			try {
				respostaCompac = udpClient.getReplay();
				flagContole = false;
				resposta = desempacotaMensagem(respostaCompac);
			} catch (SocketTimeoutException e) {
				System.out.println("tempo de envio expirado");
			}
			
		}
		contMessageId++;
		return resposta.getArgs();
	}

	public void finaliza() {
		udpClient.finaliza();
	}

	private byte[] empacotaMensagem(String objectRef, String method, byte[] args) throws UnsupportedEncodingException {

		// empacota a Mensagem de requisicao
		Mensagem msg = new Mensagem();
		msg.setMessageType(0);
		msg.setRequestId(contMessageId);
		msg.setObjectRef(objectRef);
		msg.setMethod(method);
		
		msg.setArgs(args);
		String msgJson = gson.toJson(msg);
		byte[] msgEmpac = msgJson.toString().getBytes("utf-8");
		return msgEmpac;

	}

	private Mensagem desempacotaMensagem(byte[] resposta) {

		// desempacota a mensagem de resposta
		String str = new String(resposta, java.nio.charset.StandardCharsets.UTF_8);

		JsonReader reader = new JsonReader(new StringReader(str));
		reader.setLenient(true);
		Mensagem obj = gson.fromJson(reader, Mensagem.class);
		return obj;

	}

}