package com.lab.segmentCalculation.segmentCalculation.classes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	private LineSegment lineSegment;
	private ValidationError validationError;
	
	public Response() {	}
	public Response(LineSegment lineSegment) {
		this.lineSegment = lineSegment;
	}
	public Response(ValidationError validationError) {
		this.validationError = validationError;
	}
	
	public LineSegment getLineSegment() {
		return lineSegment;
	}
	
	public void setLineSegment(LineSegment lineSegment) {
		this.lineSegment = lineSegment;
	}

	public ValidationError getValidationError() {
		return validationError;
	}

	public void setValidationError(ValidationError validationError) {
		this.validationError = validationError;
	}
}
