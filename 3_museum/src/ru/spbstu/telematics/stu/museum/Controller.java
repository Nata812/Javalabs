package ru.spbstu.telematics.stu.museum;

public class Controller implements Runnable {
	private Museum museum;
	private East east;
	private West west;

	public Controller(Museum museum, East east, West west) {
		this.museum = museum;
		this.east = east;
		this.west = west;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (museum) {
				if (museum.isOpened) {
					museum.notifyAll();
					east.open();
					west.close();
					try {
						museum.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					museum.notifyAll();
					east.close();
					west.open();
					try {
						museum.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}
}
