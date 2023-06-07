package com.example.accessingdatamysql;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerApp extends Thread{

	Socket serverClientSocket;
	int clientNo;
	boolean prime=false;
	
	ServerApp(Socket inSocket, int clientNo){
		this.serverClientSocket=inSocket;
		this.clientNo=clientNo;
	}
	
	
	public void run() {
		try {
			DataInputStream inStream=new DataInputStream(serverClientSocket.getInputStream());
			DataOutputStream outStream=new DataOutputStream(serverClientSocket.getOutputStream());
			
			String clientMessage="";
			String serverMessage="";
			
			while(!clientMessage.equals("end")) {
				clientMessage=inStream.readUTF();
				System.out.println("Checking " + clientMessage + " FromClient: " + clientNo );
				
				try {
					prime=checkPrime(Integer.parseInt(clientMessage));
				}catch(Exception e) {
					prime=false;
				}
			}
			
		}catch(Exception e) {
			
		}
		
	}
	
	private boolean checkPrime(int num) {
		return num==0;
	}
	
}
