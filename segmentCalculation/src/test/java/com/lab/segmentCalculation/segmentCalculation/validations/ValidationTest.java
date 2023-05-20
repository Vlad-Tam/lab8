package com.lab.segmentCalculation.segmentCalculation.validations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;
import com.lab.segmentCalculation.segmentCalculation.validators.Validator;

public class ValidationTest {

	private Validator validator = new Validator();
	
	String firstValue;
	String secondValue;
	String thirdValue;
	String correctValue;
	
	@BeforeEach
	public void SetUp() {
		firstValue = "";
		secondValue = "abc";
		thirdValue = "123456789";
		correctValue = "123";
	}
	
	@Test
	public void testValidationEmpty() {
		ValidationError response = new ValidationError();
		response = validator.validateParameter(firstValue, "firstX");
		assertNotNull(response);	
		
	}
	
	@Test
	public void testValidationNotNumeric() {
		ValidationError response = new ValidationError();
		response = validator.validateParameter(secondValue, "firstY");
		assertNotNull(response);
	}
	
	@Test
	public void testValidationBigValue() {
		ValidationError response = new ValidationError();
		response = validator.validateParameter(thirdValue, "secondX");
		assertNotNull(response);
	}
	
	@Test
	public void testValidationCorrect() {
		ValidationError response = new ValidationError();
		response = validator.validateParameter(correctValue, "secondY");
		assertNull(response.getErrorStatus());	
	}
}
