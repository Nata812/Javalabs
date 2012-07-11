package ru.spbstu.telematics.stu.museum;


public class MuseumModel {

	public static void main(String[] args) {
		Museum museum = new Museum();
		Director director = new Director(museum);
		West west = new West();
		East east = new East(west);
		Controller controller = new Controller(museum, east, west);
		Thread eastThread = new Thread(east);
		Thread westThread = new Thread(west);
		Thread directorThread = new Thread(director);
		Thread controllerThread = new Thread(controller);
		eastThread.start();
		westThread.start();
		directorThread.start();
		controllerThread.start();
		for (int i = 0; i < 20; i++) {
			east.enterQueue.add(new Visitor(i));
			System.out.println("Visitor"+i+" is waiting to enter");
		}
	}

}
