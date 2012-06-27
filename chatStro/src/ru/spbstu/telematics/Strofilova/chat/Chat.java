package ru.spbstu.telematics.Strofilova.chat;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class Chat {
	public static void main(String[] args) {
		final Client client = new Client("localhost", 10000);
		final ChatFrame frame = new ChatFrame(client);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if (client.isLogedIn)
						client.logOut();	// Log out on window closing
				} catch (IOException e1) {
					//e1.printStackTrace();
				}
				finally {
					System.exit(0);
				}
			}
		});
		frame.setVisible(true);
	}

}


