package Multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User {
	public static void main(String args[]) throws IOException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		Calculadora calc = new Calculadora();
		
		System.out.println("Descreva a operação => add, sub, mult ou div");
		String op = teclado.readLine();
		System.out.println("Informe o primeiro numero");
		String n1 = teclado.readLine();
		System.out.println("Informe o segundo numero");
		String n2 = teclado.readLine();
		
		
		switch (op) {
		case "add":
			System.out.println("Servidor: " + calc.add(Double.parseDouble(n1), Double.parseDouble(n2)));
			break;
		case "sub":
			System.out.println("Servidor: " + calc.sub(Double.parseDouble(n1), Double.parseDouble(n2)));
			break;
		case "mult":
			System.out.println("Servidor: " + calc.mult(Double.parseDouble(n1), Double.parseDouble(n2)));
			break;
		case "div":
			System.out.println("Servidor: " + calc.div(Double.parseDouble(n1), Double.parseDouble(n2)));
			break;
		default:
			System.out.println("Algo errado nao esta certo");
			break;
		}
	}
}