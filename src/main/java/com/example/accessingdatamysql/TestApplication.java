package com.example.accessingdatamysql;

public class TestApplication{
	
	public static void main( String[] argu) {
		
		Thread thread=new Thread() {
			 @Override
			public void run() {
				 System.out.println("123");
			}
		};

		thread.start();

	}
	
	
}
