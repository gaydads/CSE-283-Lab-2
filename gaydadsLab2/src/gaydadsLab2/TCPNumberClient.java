/**
 * 
 */
package gaydadsLab2;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author David Gayda
 *
 */
public class TCPNumberClient {


	static final int SERVER_PORT = 32150;
	Socket clientSocket = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	InetAddress serverIp = null;
	float firstNumber;
	float secondNumber;

	public TCPNumberClient() {

		askForIP();
		readTwoFloats();
		connectToServer();
		createOutputAndInputStream();
		sendTwoFloats();
		getTwoFloatsProduct();
		closeSocketAndStreams();
	}

	protected void readTwoFloats() {

		Scanner readFromConsole = new Scanner( System.in ); 

		System.out.print("Enter first number: "); 
		firstNumber = readFromConsole.nextFloat(); 
		System.out.print("Enter second number: "); 
		secondNumber = readFromConsole.nextFloat();
		readFromConsole.close();
	}

	protected void askForIP() {
		
		Scanner consoleIn = new Scanner( System.in );
		System.out.print("Enter the IP address of the server or hit enter: ");
		             String serverIpStrg = "";
		             serverIpStrg = consoleIn.nextLine();
		if( serverIpStrg.isEmpty()) { 
			serverIpStrg = "127.0.0.1";
		}
		System.out.println("\nServer IP is " + serverIpStrg);
		
		try {
			serverIp = InetAddress.getByName(serverIpStrg);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void connectToServer() {
		
		try {
			clientSocket = new Socket(serverIp, 32150);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void createOutputAndInputStream() {
		//connectToServer();
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

	protected void sendTwoFloats() {

		//Send Float 1
		try {
			dos.writeFloat(firstNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Send Float 2
		try {
			dos.writeFloat(secondNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void getTwoFloatsProduct() {
		try {
			System.out.println(dis.readFloat());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void closeSocketAndStreams() {

		try {
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TCPNumberClient();
	}

}