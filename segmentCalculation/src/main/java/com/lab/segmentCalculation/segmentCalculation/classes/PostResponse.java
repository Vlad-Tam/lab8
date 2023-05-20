package com.lab.segmentCalculation.segmentCalculation.classes;

import java.util.ArrayList;
import java.util.List;

public class PostResponse {

	private List<Response> responseList = new ArrayList<>();;
	
	private int minFirstX;
	private int maxFirstX;
	private int minFirstY;	
	private int maxFirstY;
	
	private Double minLength;
	private Double maxLength;
	private Double averageLength;
	
	public List<Response> getResponseList() {
		return responseList;
	}	

	public void addResponseList(Response response) {
		this.responseList.add(response);
	}
	public Double getMinLength() {
		return minLength;
	}
	public void setMinLength(Double minLength) {
		this.minLength = minLength;
	}
	public Double getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Double double1) {
		this.maxLength = double1;
	}
	public Double getAverageLength() {
		return averageLength;
	}
	public void setAverageLength(Double averageLength) {
		this.averageLength = averageLength;
	}
	public int getMinFirstX() {
		return minFirstX;
	}
	public void setMinFirstX(int minFirstX) {
		this.minFirstX = minFirstX;
	}
	public int getMaxFirstX() {
		return maxFirstX;
	}
	public void setMaxFirstX(int maxFirstX) {
		this.maxFirstX = maxFirstX;
	}
	public int getMinFirstY() {
		return minFirstY;
	}
	public void setMinFirstY(int minFirstY) {
		this.minFirstY = minFirstY;
	}
	public int getMaxFirstY() {
		return maxFirstY;
	}
	public void setMaxFirstY(int maxFirstY) {
		this.maxFirstY = maxFirstY;
	}

}
