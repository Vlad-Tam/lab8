package com.lab.segmentCalculation.segmentCalculation.validators;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
	private List<String> errorMessages = new ArrayList<String>();
	private String errorStatus;
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	
	public String getErrorStatus() {
		return errorStatus;
	}
	
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	
	public void addErrorMessages(String errorMessage) {
		this.errorMessages.add(errorMessage);
	}
}
