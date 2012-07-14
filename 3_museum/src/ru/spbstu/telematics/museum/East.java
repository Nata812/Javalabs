package ru.spbstu.telematics.museum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


public class East implements Runnable{
	private boolean enterOpened = false;
	public Queue<Visitor> enterQueue = new LinkedList<Visitor>();
	private West west;
	private Museum museum;
	
	public East(West west, Museum museum) {
		this.west = west;
		this.museum = museum;
	}
	
	public void open(){
		synchronized (museum) {
			enterOpened = true;
		}
	}
	
	public void close(){
		synchronized (museum) {
			enterOpened = false;			
		}
	}
	
	private void pass() throws InterruptedException{
		synchronized (museum) {			
			if (!enterQueue.isEmpty()){
				Visitor newVisitor = enterQueue.poll();
				west.exitQueue.add(newVisitor);
				System.out.println(newVisitor+" entered the museum");
			}
		}
	}
	
	@Override
	public void run() {
		while(true){
			if (enterOpened){
				try {
					pass();
					//Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//enterQueue.take();
		}
	}
	
}
