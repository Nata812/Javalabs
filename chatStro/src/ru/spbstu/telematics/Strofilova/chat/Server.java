package ru.spbstu.telematics.Strofilova.chat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	static ExecutorService pool = Executors.newCachedThreadPool();	// Thread pool for client serving
	private ServerSocket servSock;
	private ArrayList<User> users;	// A list of connected clients
	
	static Object clientsLock = new Object();	// Lock object to synchronize work with the client list
	
	public Server(int port) throws IOException {
		servSock = new ServerSocket(port);
		users = new ArrayList<User>(); 
	}
	
	public static void main(String[] args) throws IOException {
		final Server server = new Server(10000);
		while(true) {
			try {
				final Socket client = server.servSock.accept();
				pool.submit(new Runnable() {				
					@Override
					public void run() {
						try {
							server.serve(client);				// Main things are done here
						} catch (ClassNotFoundException | IOException e) {
							server.errorExit(client);			// If the client exited badly 
						}
					}
				});
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	private void serve(Socket client) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		User someOne = new User("someone", client);
		while (true)
		{
			Message m = (Message)ois.readObject();
			switch (m.type) {
				case Message.LOGIN: {		// if it is a login request
					if (!logIn(m.userName, someOne)) {	// logIn is already synchronized
						Message resp = new Message("User already exists", "server", Message.TEXT);
						someOne.send(resp);
					}
					else {
						System.out.println(m.toString());
					}
					break;
				}
				case Message.LOGOUT: {		// if it is a logout request
					logOut(m.userName);	
					System.out.println(m.toString());
					break;
				}
				case Message.TEXT: {					// An ordinary message
					if (checkReg(m.userName)){		// Check the registration
						System.out.println(m.toString());
						String toName = parsePM(m);	// parse a personal message to define if it is personal
						if (toName!=null){			// if it is
							sendPM(toName, m);
							sendPM(m.userName, m);	// send back a copy
						} else {	// if not, send everybody
							Message resp = new Message(m.text, m.userName, Message.TEXT);
							sendAll(resp);
						}
					}
					else {		// If user is not registered
						System.out.println("UNAUTHORIZED: "+m.toString());
						Message resp = new Message("You are unauthorized, please log in to chat", "server", Message.TEXT);
						someOne.send(resp);		// Tell him about it 
					}
					break;
				}
			}
		}
	}
	
	private void sendAll(Message m){		// Sends messages to everyone connected
		synchronized (clientsLock) {
		for (int i = 0; i < users.size(); i++) {
			try {
				users.get(i).send(m);
			} catch (Exception e) {
				System.out.println("ERROR: can't deliver message to "+users.get(i).getIpString());	
			}
		}
		}
	}
	
	private boolean logIn(String userName, User client) throws IOException{	// Registers a user
		synchronized (clientsLock) {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).userName.equals(userName))
					return false;
			}
			client.userName = userName;
			users.add(client);
			Message resp = new Message("Welcome to the chat server, "+userName, "server", Message.LOGIN_CONFIRM);
			client.send(resp);
			return true;
		}
	}
	
	private void logOut(String userName) throws IOException {
		synchronized (clientsLock) {
			for (int i = 0; i < users.size(); i++){
				if (users.get(i).userName.equals(userName)) {
					Message resp = new Message("Good By, "+users.get(i).userName, "server", Message.TEXT);
					users.get(i).send(resp);
					users.remove(i);
					break;
				}
			}
		}
		Message resp = new Message("User "+userName+" left the chat", "server", Message.TEXT);
		sendAll(resp);	// Tell everyone
	}
	
	private boolean checkReg(String userName) {		// Checks registration
		synchronized (clientsLock) {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).userName.equals(userName))
					return true;
			}
			return false;
		}
	}
	
	private void errorExit(Socket client){	// If an error occurred and client exited badly
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).socket == client) {
				System.out.println("Client "+users.get(i).userName+" exited badly");							
				users.remove(i);
				break;
			}
		}
	}
	
	private void sendPM(String to, Message m) throws IOException{	// Sends a message to a particular user
		boolean sentFlag = false;
		synchronized (clientsLock) {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).userName.equals(to)) {
					users.get(i).send(m);
					sentFlag = true;
				}
			}
		}
		if (!sentFlag) {	// if no such user sends a message back
			sendPM(m.userName, new Message("No such user registered", "server", Message.TEXT));
		}
	}
	
	private String parsePM(Message m){	// defines if it is a personal message and returns receiver's name 
		StringBuilder name = null;
		if (m.text.charAt(0)=='@') {	// it starts with "@userName:"
			name = new StringBuilder();
			int i = 1;
			while(!(m.text.charAt(i)==':')) {
				name.append(m.text.charAt(i));
				i++;
			}
			return name.toString();
		}
		return null;
	}
	
}