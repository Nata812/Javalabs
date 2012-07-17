package ru.spbstu.telematics.stu.museum;

public class Director implements Runnable {
	private Museum museum;

	public Director(Museum museum) {
		this.setMuseum(museum);
	}

	public synchronized void openMuseum() {
		synchronized (museum) {
			museum.notifyAll();
			getMuseum().isOpened = true;
			System.out.println("Open the museum!");
			try {
				getMuseum().wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void closeMuseum() {
		synchronized (museum) {
			museum.notifyAll();
			getMuseum().isOpened = false;
			System.out.println("Close the museum!");
			try {
				getMuseum().wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (true) {
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

	public Museum getMuseum() {
		return museum;
	}

	public void setMuseum(Museum museum) {
		this.museum = museum;
	}
}
