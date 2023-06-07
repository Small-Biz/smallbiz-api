package com.example.accessingdatamysql;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] arvg) throws IOException {
		
		//Listen to port
		ServerSocket server=new ServerSocket(8699);
		
		int count=0;
		System.out.println("Server Started...");
		
		while(true) {
			count++;
			
			Socket serverClientSocket =server.accept();
			System.out.println("Server Client: " + count);
			
			ServerApp sa = new ServerApp(serverClientSocket,count);
			sa.start();
			
		}
		
	}
	
}
