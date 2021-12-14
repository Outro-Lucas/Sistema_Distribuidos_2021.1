package Proxy;

import java.util.Scanner;


public class User {
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Digite a opera��o: ");
		String oper = scan.next();  
		
		System.out.print("Digite o primeiro operando: ");
		Double valor1 = scan.nextDouble();

		System.out.print("Digite o segundo operando: ");
		Double valor2 = scan.nextDouble();

		Double result = null ;
		Proxy proxy = new Proxy();
		if(oper.equals("+")){
			result = proxy.add(valor1,valor2);		
		}
		else if(oper.equals("-")){
			result = proxy.sub(valor1,valor2);	
		}
		else if(oper.equals("*")){
			result = proxy.mult(valor1,valor2);
		}
		else if(oper.equals("/")){
			
			result = proxy.div(valor1,valor2);
		}
		else{
			System.out.println("Opera��o Inv�lida\n");
		}


		System.out.println("Received: "+ result) ; 

		proxy.close();		
	}
}