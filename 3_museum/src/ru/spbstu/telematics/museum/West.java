package ru.spbstu.telematics.museum;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;


public class West implements Runnable {
	private boolean exitOpened = false;
	public Queue<Visitor> exitQueue = new LinkedList<Visitor>();
	private Museum museum;
	public West(Museum museum) {
		this.museum = museum;
	}
	public void open() {
		synchronized (museum) {
			exitOpened = true;
		}
	}

	public void close() {
		synchronized (museum) {
			exitOpened = false;
		}
	}

	@Override
	public void run() {
		while (true) {
			if (exitOpened) {
				try {
					pass();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void pass() throws InterruptedException {
		if (!exitQueue.isEmpty()) {
			Visitor oldVisitor = exitQueue.poll();
			System.out.println(oldVisitor + " left the museum");
			//Thread.sleep(2000);
		}
	}

}
