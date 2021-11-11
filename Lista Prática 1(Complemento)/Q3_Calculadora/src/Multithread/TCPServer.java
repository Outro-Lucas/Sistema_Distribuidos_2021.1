package Multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    
	public static void main (String args[]) {
		Socket clientSocket;
		try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
		while(true) {
			clientSocket = listenSocket.accept();
			Connection c = new Connection(clientSocket);
		}
	} catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
    
	
    
}

class Connection extends Thread {
	Socket s;
	Calculadora calculadora = Calculadora.getInstance();
	public Connection (Socket aClientSocket) {
	    s = aClientSocket;
		this.start();
	}
	
	public String getRequest() throws IOException {
		DataInputStream in = new DataInputStream(s.getInputStream());
		String n = in.readUTF();
		return n;
	}
	
	public void sendResponse(String response) throws IOException {
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF(response);
	}
	
	public void run(){
	    try {			          
	    	String data = getRequest();   	
	    } catch(EOFException e) {System.out.println("EOF:"+e.getMessage());
	    } catch(IOException e) {System.out.println("IO:"+e.getMessage());
	    } finally{ try {s.close();}catch (IOException e){/*close failed*/}}
	}
}