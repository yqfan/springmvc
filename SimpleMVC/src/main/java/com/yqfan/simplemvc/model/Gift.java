package com.yqfan.simplemvc.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class Gift {
	long id;
	String title;
	String description;
	String dataUrl;
	String oriName;
	String owner;
	long touchCount;
	String votedUserUrl;
	HashSet<String> votedUser; //contains user names who have voted for this gift
	
	public Gift(String t, String d) {
		title = t;
		description = d;
		id = 0;
		dataUrl = "";
		oriName = "";
		owner = "1";
		touchCount = 0;
		votedUserUrl = "";
		votedUser = new HashSet<String>();
	}
	
	public void setId(long x) {
		this.id = x;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDataUrl(String s) {
		this.dataUrl = s;
	}
	
	public String getDataUrl() {
		return this.dataUrl;
	}
	
	public void setOriName(String s) {
		this.oriName = s;
	}
	
	public String getOriName() {
		return oriName;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public long getTouchCount() {
		return touchCount;
	}
	
	public void setTouchCount(long touchCount) {
		this.touchCount = touchCount;
	}
	
	public void incrementTouchCount() {
		++touchCount;
	}
	
	public void setVotedUserUrl(String votedUserUrl) {
		this.votedUserUrl = votedUserUrl;
	}
	
	public String getVotedUserUrl() {
		return votedUserUrl;
	}
	
	public HashSet<String> getVotedUser() {
		// unserialize voted user set from the file "votedUserUrl"
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(votedUserUrl));
			System.out.println("votedUserUrl="+votedUserUrl);
			votedUser = (HashSet<String>) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return votedUser;
	}
	
	public void saveVotedUser() {
		// serialize the voted user set to file "votedUserUrl"
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(votedUserUrl));
			oos.writeObject(votedUser);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
