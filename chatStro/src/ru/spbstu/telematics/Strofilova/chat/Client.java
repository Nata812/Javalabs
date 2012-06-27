package ru.spbstu.telematics.Strofilova.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JTextArea;


public class Client {
	private Socket clientSock = new Socket();
	private String serverAddress;
	private int port;
	private ObjectOutputStream clientOutput;
	private ObjectInputStream clientInput;
	
	public boolean isLogedIn = false;
	public String userName;
	
	public Client(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = port;
	}
	
	public boolean connect() {	// connects to the server
		try {
			clientSock.connect(new InetSocketAddress(serverAddress, port));
		} catch (IOException e) {
			return false;
		}
		try {
			clientOutput = new ObjectOutputStream(clientSock.getOutputStream());	// Opening streams
			clientInput = new ObjectInputStream(clientSock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean send(String text, String from){	
		try {
			clientOutput.writeObject(new Message(text, from, Message.TEXT));
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public void recieve(JTextArea textArea) throws IOException {
		while(true) {
			try {
				Message resp = (Message) clientInput.readObject();	
				if (resp.type == Message.LOGIN_CONFIRM) {	// If it is a login confirmation message 
					isLogedIn = true;						//set client logged in
				}
				textArea.append(resp.userName+": "+resp.text+"\n");		// Print the message
			} catch (IOException | ClassNotFoundException e) {
				textArea.append("Lost server connection\n");
				break;
			} finally {
				textArea.setCaretPosition(textArea.getDocument().getLength()); // Set caret position to the last 
			}																	// message
		}
	}
	
	public void logIn(String userName) throws IOException{	// Send login request
		this.userName = userName;
		clientOutput.writeObject(new Message("", userName, Message.LOGIN));
	}
	
	public void logOut() throws IOException{				// Send logout request
		Message m = new Message("", this.userName, Message.LOGOUT);
		clientOutput.writeObject(m);
		isLogedIn = false;
	}
	
	
}
