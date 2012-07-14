package ru.spbstu.telematics.museum;

public class Director implements Runnable{
	private Museum museum;
	public Director(Museum museum) {
		this.museum = museum;
	}
	
	public void openMuseum() {
		synchronized (museum) {
			museum.isOpened = true;
			System.out.println("Open the museum!");
		}
	}
	
	public void closeMuseum() {
		synchronized (museum) {
			museum.isOpened = false;
			System.out.println("Close the museum!");
		}
	}

	@Override
	public void run() {
		while(true) {
			closeMuseum();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			openMuseum();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
