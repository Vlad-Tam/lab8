package com.lab.segmentCalculation.segmentCalculation.classes;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntResponce {
	private int size;
	private String message;
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getMessage() {
		return message;
	}
	
}
