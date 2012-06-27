package ru.spbstu.telematics.Strofilova.chat;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int TEXT = 0;
	public static final int LOGIN = 1;
	public static final int LOGOUT = 2;
	public static final int LOGIN_CONFIRM = 3;
	
	public String text;
	public String userName;
	public Date timeStamp;
	public int type;
	public Message(String text, String userName, int type) {
		this.text = text;
		this.userName = userName;
		this.timeStamp = new Date();
		this.type = type;
	}
	@Override
	public String toString() {
		return this.timeStamp.toString()+" : "+this.type+":: "+this.userName+" : "+this.text+" ";
	}
}
