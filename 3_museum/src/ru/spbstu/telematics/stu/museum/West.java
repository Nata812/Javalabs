package ru.spbstu.telematics.stu.museum;

import java.util.LinkedList;
import java.util.Queue;

public class West implements Runnable {
	private boolean exitOpened = false;
	public Queue<Visitor> exitQueue = new LinkedList<Visitor>();

	public void open() {
		exitOpened = true;
	}

	public void close() {
		exitOpened = false;
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
			// Thread.sleep(2000);
		}
	}

}
