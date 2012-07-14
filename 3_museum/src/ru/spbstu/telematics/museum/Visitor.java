package ru.spbstu.telematics.museum;

public class Visitor {
	public int num;
	public Visitor(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Visitor"+num;
	}
}
