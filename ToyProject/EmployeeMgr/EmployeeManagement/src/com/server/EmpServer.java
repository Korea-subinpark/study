package com.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.emp.Employee;

public class EmpServer {
	ServerSocket server;
	Socket client;
	InputStream in;
	ObjectInputStream ois;
	private int port = 6000;

	public void receive() {
		try {
			server = new ServerSocket(port);
			System.out.println("ServerSocket ok. port=" + port);
			
			while(true) {
				System.out.println("server ready...");
				client = server.accept();
				in = client.getInputStream();
				ois = new ObjectInputStream(in);
				
				ArrayList<Employee> temp = new ArrayList<>();
				temp = (ArrayList<Employee>) ois.readObject();
				for (int i = 0; i < temp.size(); i++) {
					Employee e = temp.get(i);
					System.out.println(e);
				}
				System.out.println("receive ok");
				
			}
			
		} catch (Exception e) {
			System.out.println("서버 에러");
		}
	}
	public static void main(String[] args) {
		new EmpServer().receive();
	}

}