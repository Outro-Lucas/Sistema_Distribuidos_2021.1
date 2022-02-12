package servidor;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class PassagemEsqueleto {

	RodoviariaServente servente;
	Gson gson = new Gson();

	public PassagemEsqueleto() {
		servente = new RodoviariaServente();
	}

	public byte[] novaPassagem(byte[] args) throws UnsupportedEncodingException {

		String str = new String(args, java.nio.charset.StandardCharsets.UTF_8);
		JsonReader reader = new JsonReader(new StringReader(str));
		reader.setLenient(true);
		Passagem passagem = gson.fromJson(reader, Passagem.class);
		String result = servente.novaPassagem(passagem);

		// (1) Desempacota argumento de entrada
		// (2) chama o metodo do servente
		String resulJson = gson.toJson(result);
		byte[] resultEmpac = resulJson.toString().getBytes("utf-8");
		// (3) empacota resposta do método servente e retorna
		return resultEmpac;
	}

	public byte[] consultarPassagem(byte[] args) throws UnsupportedEncodingException {

		String str = new String(args, java.nio.charset.StandardCharsets.UTF_8);
		JsonReader reader = new JsonReader(new StringReader(str));
		reader.setLenient(true);
		Object numPassagem = gson.fromJson(reader, Object.class);

		Passagem result = servente.consultarPassagem(numPassagem.toString());

		// (1) Desempacota argumento de entrada
		// (2) chama o metodo do servente
		// (3) empacota resposta do método servente e retorna
		if (result != null) {
			String resulJson = gson.toJson(result);
			byte[] resultEmpac = resulJson.toString().getBytes("utf-8");
			return resultEmpac;
		} else {
			String resulJson = gson.toJson(null);
			byte[] resultEmpac = resulJson.toString().getBytes("utf-8");
			return resultEmpac;
		}
	}

	public byte[] editarPassagem(byte[] args) throws Exception {
		String str = new String(args, java.nio.charset.StandardCharsets.UTF_8);
		JsonReader reader = new JsonReader(new StringReader(str));
		reader.setLenient(true);
		Passagem passagem = gson.fromJson(reader, Passagem.class);
		String result = servente.editarPassagem(passagem);

		// (1) Desempacota argumento de entrada
		// (2) chama o metodo do servente
		String resulJson = gson.toJson(result);
		byte[] resultEmpac = resulJson.toString().getBytes("utf-8");
		// (3) empacota resposta do m�todo servente e retorna
		return resultEmpac;
	}
}