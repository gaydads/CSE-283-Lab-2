/**
 * 
 */
package gaydadsLab2;

import java.io.*;
import java.net.*;

/**
 * @author David Gayda
 *
 */
public class TCPNumberServer {

	static final int SERVER_PORT = 32150;
	
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	Float f1 = null;
	Float f2 = null;
	Boolean listening = true;
	/**
	 * @param args
	 */
	
	public TCPNumberServer() {
		createServerSocket();
		displayContactInfo();
		listenForClients();
		closeServer();
	}
	
	protected void createServerSocket() {
		
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void displayContactInfo() {
		
		try { 
			 // Display contact information. 
			 System.out.println( 
			 "Number Server standing by to accept Clients:" 
			 
			 + "\nIP : " + InetAddress.getLocalHost() 
			 + "\nPort: " + serverSocket.getLocalPort() 
			 + "\n\n" ); 
			 
			 } catch (UnknownHostException e) { 
			 // NS lookup for host IP failed? 
			 // This should only happen if the host machine does 
			 // not have an IP address. 
			 e.printStackTrace(); 
			 }
	}
	
	protected void listenForClients() {
		
		
		do {
			if (listening == true) {
				System.out.println("Listening...");
			}
			
			handleOneClient();
			
			if (f1 < 0 || f2 < 0) {
				listening = false;
				System.out.println("Server Closed");
			}
			
		} while(listening);

		
	}
	
	protected void handleOneClient() {
		try {
			clientSocket = serverSocket.accept();
			System.out.println("\nConnected to Client" + clientSocket.getInetAddress() +" " + clientSocket.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createClientStreams();
		sendAndReceiveNumbers();
		closeClientConnection();
	}
	
	protected void createClientStreams() {
		
		try {
			dos = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dis = new DataInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void sendAndReceiveNumbers() {
		try {
			
			f1 = dis.readFloat();
			System.out.println("Float 1 = " + f1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			f2 = dis.readFloat();
			System.out.println("Float 2 = " + f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Float product = f1 * f2;
		
		try {
			dos.writeFloat(product);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void closeClientConnection() {
		try {
			dis.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finished Client" + clientSocket.getInetAddress() +" " + clientSocket.getLocalPort() + "\n");
	}
	
	protected void closeServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TCPNumberServer();
	}

}
