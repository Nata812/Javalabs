package ru.spbstu.telematics.stu.museum;

import java.util.LinkedList;
import java.util.Queue;

public class East implements Runnable {
	private boolean enterOpened = false;
	public Queue<Visitor> enterQueue = new LinkedList<Visitor>();
	private West west;

	public East(West west) {
		this.west = west;
	}

	public void open() {
		enterOpened = true;
	}

	public void close() {
		enterOpened = false;
	}

	private void pass() throws InterruptedException {
		if (!enterQueue.isEmpty()) {
			Visitor newVisitor = enterQueue.poll();
			west.exitQueue.add(newVisitor);
			System.out.println(newVisitor + " entered the museum");
		}
	}

	@Override
	public void run() {
		while (true) {
			if (enterOpened) {
				try {
					pass();
					// Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// enterQueue.take();
		}
	}

}
