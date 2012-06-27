package ru.spbstu.telematics.museum;

public class Controller implements Runnable{
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
		while(true) {
			if (museum.isOpened) {
					east.open();
					west.close();
			} else {
					east.close();
					west.open();
			}
		}
	}
}
