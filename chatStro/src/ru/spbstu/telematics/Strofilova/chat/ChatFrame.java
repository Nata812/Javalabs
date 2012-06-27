package ru.spbstu.telematics.Strofilova.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class ChatFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 640;
	public static final int DEFAULT_HEIGHT = 480;
	private boolean isConnected = false;
	private Client client;
	public Thread listener;
	
	public ChatFrame(Client client_) {	// Constructor
		this.client = client_;
		isConnected = client.connect();
		
		/// Setting window parameters
		
		setTitle("Chat");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setLocation(screenWidth/4, screenHeight/4);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		/// On the top of the window - user name input field
		
		JPanel header = new JPanel();
		header.setLayout(new GridLayout(1, 2));
		header.add(new JLabel("User name:", SwingConstants.LEFT));
		final JTextField userNameField = new JTextField();
		header.add(userNameField);
		add(header, BorderLayout.NORTH);
		
		/// In the center - a text area
		
		final JTextArea textArea = new JTextArea(23, 25);
		textArea.setEditable(false);
		JScrollPane scroller = new JScrollPane(textArea);
		add(scroller, BorderLayout.CENTER);
		
		/// On the bottom - a text field to enter a message 
		
		JPanel footer = new JPanel();
		footer.setLayout(new GridLayout(1,1));
		final JTextField messageField = new JTextField();
		footer.add(messageField);
		add(footer, BorderLayout.SOUTH);
		
		/// Action listeners - set up everything on the Enter key press
		
		// Login
		userNameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!userNameField.getText().equals("") & !userNameField.getText().equals(client.userName)){
					try {
						if (client.isLogedIn)
							client.logOut();
						client.logIn(userNameField.getText());
						client.userName=userNameField.getText();
					} catch (IOException e1) {
						textArea.append("Can't log in!");
					}
					messageField.requestFocus();
				}
			}
		});
		
		// Message sending
		messageField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isConnected && !messageField.getText().equals("")) {
					if (!client.send(messageField.getText(), client.userName))
						textArea.append("Error sending message");
				}
				messageField.setText("");
			}
		});
		
		new Thread(new Runnable() {	// Thread that listens to the server's messages
			
			@Override
			public void run() {
				if (isConnected) {
					try {
						client.recieve(textArea);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		if (!isConnected)		// If not connected, tell the user
			textArea.append("Cannot connect to server\n");
	}
	
}
