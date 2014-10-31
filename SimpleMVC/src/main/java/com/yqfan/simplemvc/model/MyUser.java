package com.yqfan.simplemvc.model;

public class MyUser {
	private String userName;
	private String passWord;
	private String role;
	private long totalVotes;
	
	public MyUser() {
		totalVotes = 0;
	}
	 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassWord() {
    	return passWord;
    }
    
    public void setPassWord(String password) {
    	this.passWord = password;
    }
    
    public String getRole() {
    	return role;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    
    public long getTotalVotes() {
    	return totalVotes;
    }
    
    public void setTotalVotes(long x) {
    	this.totalVotes = x;
    }
    
    public void incrementTotalVotes() {
    	++totalVotes;
    }
}
