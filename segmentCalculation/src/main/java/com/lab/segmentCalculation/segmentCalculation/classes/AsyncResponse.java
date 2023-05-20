package com.lab.segmentCalculation.segmentCalculation.classes;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsyncResponse {
	private Integer id;
	private String message;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public AsyncResponse(){
		this.id = null;
		this.message = null;
	}
}
