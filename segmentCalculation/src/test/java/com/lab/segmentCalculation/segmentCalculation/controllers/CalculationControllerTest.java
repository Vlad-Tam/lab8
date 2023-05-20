package com.lab.segmentCalculation.segmentCalculation.controllers;

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

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.Point;
import com.lab.segmentCalculation.segmentCalculation.classes.PostParams;
import com.lab.segmentCalculation.segmentCalculation.classes.PostResponse;
import com.lab.segmentCalculation.segmentCalculation.classes.Response;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;
import com.lab.segmentCalculation.segmentCalculation.services.CalculationService;
import com.lab.segmentCalculation.segmentCalculation.services.DataBaseService;
import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;
import com.lab.segmentCalculation.segmentCalculation.validators.Validator;
import java.lang.ArithmeticException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CalculationControllerTest {
	private CalculationService calculationService = mock(CalculationService.class);
	private Validator validator = mock(Validator.class);
	private InMemoryCache inMemoryCache = mock(InMemoryCache.class);
	private DataBaseService dataBaseService = mock(DataBaseService.class);

	@InjectMocks
	private CalculationController testController = new CalculationController(calculationService, dataBaseService, validator, inMemoryCache);

	
	@Test
	public void controllerTest() {

		String firstX = "3";
		String firstY = "4";
		String secondX = "6";
		String secondY = "8";

		ValidationError buff = new ValidationError();
		when(validator.validateParameter(firstX, "firstX")).thenReturn(buff);
		when(validator.validateParameter(firstY, "firstY")).thenReturn(buff);
		when(validator.validateParameter(secondX, "secondX")).thenReturn(buff);
		when(validator.validateParameter(secondY, "secondY")).thenReturn(buff);

		Point point1 = new Point(Integer.parseInt(firstX), Integer.parseInt(firstY));
		Point point2 = new Point(Integer.parseInt(secondX), Integer.parseInt(secondY));
		LineSegment lineSegment = new LineSegment(point1, point2);
		lineSegment.setProjectionX(3);
		lineSegment.setProjectionY(4);
		lineSegment.setLength((double) 5);

		when(calculationService.calculation(Integer.parseInt(firstX), Integer.parseInt(firstY),
				Integer.parseInt(secondX), Integer.parseInt(secondY))).thenReturn(lineSegment);
		Mockito.doNothing().when(dataBaseService).addLineSegment(lineSegment);
		Mockito.doNothing().when(dataBaseService).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
		
		ResponseEntity<Response> result = testController.calculationController(firstX, firstY, secondX, secondY);

		assertNotNull(result);
		assertEquals(lineSegment.getLength(), result.getBody().getLineSegment().getLength());
		assertEquals(lineSegment.getProjectionX(), result.getBody().getLineSegment().getProjectionX());
		assertEquals(lineSegment.getProjectionY(), result.getBody().getLineSegment().getProjectionY());
		verify(dataBaseService, times(1)).addLineSegment(lineSegment);
		verify(dataBaseService, times(0)).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
	}
	
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
		Mockito.doNothing().when(dataBaseService).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
			
		ResponseEntity<Response> result = testController.calculationController(firstX, firstY, secondX, secondY);

		assertNotNull(response);
		assertNotNull(result);
		assertNotNull(result.getBody().getValidationError().getErrorMessages());
		assertNull(result.getBody().getLineSegment());
		verify(dataBaseService, times(1)).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
	}

	@Test
	public void controllerNullErrorTest() {

		String firstX = "1";
		String firstY = "2";
		String secondX = "1";
		String secondY = "2";

		ValidationError buff = new ValidationError();
		
		when(validator.validateParameter(firstX, "firstX")).thenReturn(buff);
		when(validator.validateParameter(firstY, "firstY")).thenReturn(buff);
		when(validator.validateParameter(secondX, "secondX")).thenReturn(buff);
		when(validator.validateParameter(secondY, "secondY")).thenReturn(buff);
		
		LineSegment lineSegment = new LineSegment();
		lineSegment.setProjectionX(0);
		lineSegment.setProjectionY(0);
		lineSegment.setLength((double) 0);
		
		when(calculationService.calculation(Integer.parseInt(firstX), Integer.parseInt(firstY),
				Integer.parseInt(secondX), Integer.parseInt(secondY))).thenReturn(lineSegment);
		Mockito.doNothing().when(dataBaseService).addLineSegment(lineSegment);
		ResponseEntity<Response> result = testController.calculationController(firstX, firstY, secondX, secondY);

		assertNotNull(result);
		assertNull(result.getBody().getLineSegment());
		assertNotNull(result.getBody().getValidationError().getErrorMessages());
		assertEquals("Length is not found. Points must be different", result.getBody().getValidationError().getErrorMessages().get(0));
		verify(dataBaseService, times(0)).addLineSegment(lineSegment);
		
	}
	
	@Test
	public void controllerExceptionTest() {

		String firstX = "1";
		String firstY = "0";
		String secondX = "1";
		String secondY = "-2147483648";

		ValidationError buff = new ValidationError();
		
		when(validator.validateParameter(firstX, "firstX")).thenReturn(buff);
		when(validator.validateParameter(firstY, "firstY")).thenReturn(buff);
		when(validator.validateParameter(secondX, "secondX")).thenReturn(buff);
		when(validator.validateParameter(secondY, "secondY")).thenReturn(buff);
		
		when(calculationService.calculation(Integer.parseInt(firstX), Integer.parseInt(firstY),
				Integer.parseInt(secondX), Integer.parseInt(secondY))).thenThrow(ArithmeticException.class);
		ResponseEntity<Response> result = testController.calculationController(firstX, firstY, secondX, secondY);
		assertNotNull(result.getBody().getValidationError().getErrorMessages());
		assertEquals("Internal Server Error happend", result.getBody().getValidationError().getErrorMessages().get(0));
	}
	
	@Test
	public void postControllerTest() {
		
		List<PostParams> params = new ArrayList<>();
		PostParams buff1 = new PostParams("3", "4", "6", "8");		
		params.add(buff1);

		ValidationError buff = new ValidationError();
		when(validator.validateParameter(params.get(0).getFirstX(), "firstX")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getFirstY(), "firstY")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getSecondX(), "secondX")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getSecondY(), "secondY")).thenReturn(buff);
		
		buff.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		Point point1 = new Point(Integer.parseInt(params.get(0).getFirstX()), Integer.parseInt(params.get(0).getFirstY()));
		Point point2 = new Point(Integer.parseInt(params.get(0).getSecondX()), Integer.parseInt(params.get(0).getSecondY()));
		LineSegment lineSegment = new LineSegment(point1, point2);
		lineSegment.setProjectionX(3);
		lineSegment.setProjectionY(4);
		lineSegment.setLength((double) 5);

		when(calculationService.postLineSegment(buff1)).thenReturn(lineSegment);
		Mockito.doNothing().when(dataBaseService).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
		Mockito.doNothing().when(dataBaseService).addLineSegment(lineSegment);
		ResponseEntity<PostResponse> result = testController.postController(params);
		assertNotNull(result);
		assertEquals(lineSegment.getLength(), result.getBody().getResponseList().get(0).getLineSegment().getLength());
		assertEquals(lineSegment.getProjectionX(), result.getBody().getResponseList().get(0).getLineSegment().getProjectionX());
		assertEquals(lineSegment.getProjectionY(), result.getBody().getResponseList().get(0).getLineSegment().getProjectionY());
		verify(dataBaseService, times(1)).addLineSegment(lineSegment);
		verify(dataBaseService, times(0)).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
	}
	
	
	@Test
	public void postControllerErrorTest() {
		
		List<PostParams> params = new ArrayList<>();
		PostParams buff1 = new PostParams("1111", "123", "12", "423");
		
		params.add(buff1);

		
		ValidationError buffErr = new ValidationError();
		buffErr.getErrorMessages().add("Coordinate secondX must be less then 1000");
		buffErr.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		
		
		ValidationError buff = new ValidationError();
		when(validator.validateParameter(params.get(0).getFirstX(), "firstX")).thenReturn(buffErr);
		when(validator.validateParameter(params.get(0).getFirstY(), "firstY")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getSecondX(), "secondX")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getSecondY(), "secondY")).thenReturn(buff);
		
		buff.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		Point point1 = new Point(Integer.parseInt(params.get(0).getFirstX()), Integer.parseInt(params.get(0).getFirstY()));
		Point point2 = new Point(Integer.parseInt(params.get(0).getSecondX()), Integer.parseInt(params.get(0).getSecondY()));
		LineSegment lineSegment = new LineSegment(point1, point2);
		lineSegment.setProjectionX(3);
		lineSegment.setProjectionY(4);
		lineSegment.setLength((double) 5);

		when(calculationService.postLineSegment(buff1)).thenReturn(lineSegment);
		Mockito.doNothing().when(dataBaseService).addLineSegment(lineSegment);
		Mockito.doNothing().when(dataBaseService).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
		
		ResponseEntity<PostResponse> result = testController.postController(params);
		
		assertNotNull(result);
		assertEquals(1, result.getBody().getResponseList().get(0).getValidationError().getErrorMessages().size());
		verify(dataBaseService, times(0)).addLineSegment(lineSegment);
		verify(dataBaseService, times(1)).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
	}
	
	@Test
	public void postControllerNullLengthTest() {
		
		List<PostParams> params = new ArrayList<>();
		PostParams buff1 = new PostParams("5", "5", "5", "5");		
		params.add(buff1);
			
		ValidationError buff = new ValidationError();
		when(validator.validateParameter(params.get(0).getFirstX(), "firstX")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getFirstY(), "firstY")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getSecondX(), "secondX")).thenReturn(buff);
		when(validator.validateParameter(params.get(0).getSecondY(), "secondY")).thenReturn(buff);
		
		buff.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		Point point1 = new Point(Integer.parseInt(params.get(0).getFirstX()), Integer.parseInt(params.get(0).getFirstY()));
		Point point2 = new Point(Integer.parseInt(params.get(0).getSecondX()), Integer.parseInt(params.get(0).getSecondY()));
		LineSegment lineSegment = new LineSegment(point1, point2);
		lineSegment.setProjectionX(0);
		lineSegment.setProjectionY(0);
		lineSegment.setLength((double) 0);

		when(calculationService.postLineSegment(buff1)).thenReturn(lineSegment);
		Mockito.doNothing().when(dataBaseService).addLineSegment(lineSegment);
		Mockito.doNothing().when(dataBaseService).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
		
		ResponseEntity<PostResponse> result = testController.postController(params);
		
		assertNotNull(result);
		assertEquals(1, result.getBody().getResponseList().get(0).getValidationError().getErrorMessages().size());
		verify(dataBaseService, times(1)).addLineSegment(lineSegment);
		verify(dataBaseService, times(0)).addError(any(String.class),any(String.class),any(String.class),any(String.class),any(ValidationError.class));
	}
}
