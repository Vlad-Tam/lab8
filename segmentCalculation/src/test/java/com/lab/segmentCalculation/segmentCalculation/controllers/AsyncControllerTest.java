package com.lab.segmentCalculation.segmentCalculation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lab.segmentCalculation.segmentCalculation.classes.AsyncResponse;
import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.Point;
import com.lab.segmentCalculation.segmentCalculation.classes.Response;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;
import com.lab.segmentCalculation.segmentCalculation.jpa.IdResponseEntity;
import com.lab.segmentCalculation.segmentCalculation.services.AsyncService;
import com.lab.segmentCalculation.segmentCalculation.services.CalculationService;
import com.lab.segmentCalculation.segmentCalculation.services.DataBaseService;
import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;
import com.lab.segmentCalculation.segmentCalculation.validators.Validator;

public class AsyncControllerTest {
	private CalculationService calculationService = mock(CalculationService.class);
	private Validator validator = mock(Validator.class);
	private AsyncService asyncService = mock(AsyncService.class);

	@InjectMocks
	private AsyncController testController = new AsyncController(calculationService, validator, asyncService);
	
	/*
	 * @Test public void controllerTest() {
	 * 
	 * String firstX = "3"; String firstY = "4"; String secondX = "6"; String
	 * secondY = "8";
	 * 
	 * ValidationError buff = new ValidationError();
	 * when(validator.validateParameter(firstX, "firstX")).thenReturn(buff);
	 * when(validator.validateParameter(firstY, "firstY")).thenReturn(buff);
	 * when(validator.validateParameter(secondX, "secondX")).thenReturn(buff);
	 * when(validator.validateParameter(secondY, "secondY")).thenReturn(buff);
	 * 
	 * Point point1 = new Point(Integer.parseInt(firstX), Integer.parseInt(firstY));
	 * Point point2 = new Point(Integer.parseInt(secondX),
	 * Integer.parseInt(secondY)); LineSegment lineSegment = new LineSegment(point1,
	 * point2); lineSegment.setProjectionX(3); lineSegment.setProjectionY(4);
	 * lineSegment.setLength((double) 5); //AsyncResponse asyncResponse = new
	 * AsyncResponse();
	 * 
	 * when(calculationService.calculation(Integer.parseInt(firstX),
	 * Integer.parseInt(firstY), Integer.parseInt(secondX),
	 * Integer.parseInt(secondY))).thenReturn(lineSegment);
	 * 
	 * Mockito.doNothing().when(asyncService).addIdRespRep(any(IdResponseEntity.
	 * class));
	 * 
	 * ResponseEntity<AsyncResponse> result =
	 * testController.calculationController(firstX, firstY, secondX, secondY);
	 * 
	 * assertNotNull(result); //assertNotNull(result.getBody().getId());
	 * //assertEquals("Success performance", result.getBody().getMessage());
	 * //verify(asyncService, times(1)).addIdRespRep(any(IdResponseEntity.class));
	 * 
	 * }
	 */
	
	@Test
	public void controllerErrorTest() {

		String firstX = "";
		String firstY = "abc";
		String secondX = "123456789";
		String secondY = "123";

		ValidationError response = new ValidationError();

		ValidationError buff1 = new ValidationError();
		buff1.getErrorMessages().add("Coordinate firstX is Empty");
		buff1.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		ValidationError buff2 = new ValidationError();
		buff2.getErrorMessages().add("Coordinate firstY must be a number");
		buff2.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		ValidationError buff3 = new ValidationError();
		buff3.getErrorMessages().add("Coordinate secondX must be less then 1000");
		buff3.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		ValidationError buff4 = new ValidationError();
		buff4.getErrorMessages().add("Coordinate secondY is Empty");
		buff4.setErrorStatus(HttpStatus.OK.name());
		
		when(validator.validateParameter(firstX, "firstX")).thenReturn(buff1);
		response.getErrorMessages().addAll(buff1.getErrorMessages());
		response.setErrorStatus(buff1.getErrorStatus());
		buff1.getErrorMessages().clear();
		
		when(validator.validateParameter(firstY, "firstY")).thenReturn(buff2);
		response.getErrorMessages().addAll(buff2.getErrorMessages());
		response.setErrorStatus(buff2.getErrorStatus());
		buff2.getErrorMessages().clear();
		
		when(validator.validateParameter(secondX, "secondX")).thenReturn(buff3);
		response.getErrorMessages().addAll(buff3.getErrorMessages());
		response.setErrorStatus(buff3.getErrorStatus());
		buff3.getErrorMessages().clear();
		
		when(validator.validateParameter(secondY, "secondY")).thenReturn(buff4);
			
		ResponseEntity<AsyncResponse> result = testController.calculationController(firstX, firstY, secondX, secondY);

		assertNotNull(result.getBody().getMessage());
	}
}
