package ru.spbstu.telematics.Strofilova.chat;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class User {
	public Socket socket = null;
	public String userName;
	private ObjectOutputStream oos;
	public User(String userName, Socket socket) {
		this.socket = socket;
		this.userName = userName;
		try {
			oos = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Message m) throws IOException {
		oos.writeObject(m);
	}
	
	public String getIpString() {
		return socket.getInetAddress().toString();
	}
}
