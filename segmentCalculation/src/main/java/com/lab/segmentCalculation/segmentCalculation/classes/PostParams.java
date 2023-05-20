package com.lab.segmentCalculation.segmentCalculation.classes;

public class PostParams {
	
	private String firstX;
	private String firstY;
	private String secondX;
	private String secondY;
	
	public PostParams() {}
	public PostParams(String firstX, String firstY, String secondX, String secondY) {
		this.firstX = firstX;
		this.firstY = firstY;
		this.secondX = secondX;
		this.secondY = secondY;
	}
	
	public String getFirstX() {
		return firstX;
	}	
	public String getFirstY() {
		return firstY;
	}	
	public String getSecondX() {
		return secondX;
	}	
	public String getSecondY() {
		return secondY;
	}
		
}
