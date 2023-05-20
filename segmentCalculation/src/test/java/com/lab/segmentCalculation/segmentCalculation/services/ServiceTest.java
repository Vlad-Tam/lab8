package com.lab.segmentCalculation.segmentCalculation.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.PostParams;
import com.lab.segmentCalculation.segmentCalculation.jpa.LineSegmentEntity;
import com.lab.segmentCalculation.segmentCalculation.jpa.LineSegmentRepository;
import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;
import com.lab.segmentCalculation.segmentCalculation.jpa.ErrorEntity;

public class ServiceTest {

	private LaunchCounterService counterService = new LaunchCounterService();
	private CalculationService service = new CalculationService(counterService);
	private DataBaseService dataBaseService = new DataBaseService();
	private LineSegment firstLineSegment;
	private LineSegment secondLineSegment;
	private LineSegment thirdLineSegment;
	private LineSegment fourthLineSegment;

	@BeforeEach
	public void setUp() {
		firstLineSegment = service.calculation(3, 4, 6, 8);
		secondLineSegment = service.calculation(1, 2, 1, 2);
		thirdLineSegment = service.postLineSegment(new PostParams("3", "4", "6", "8"));
		fourthLineSegment = service.calculation(0, 6, 8, 0);
	}

	@Test
	public void testCalculation() {

		assertNotNull(firstLineSegment);
		assertEquals(3, firstLineSegment.getProjectionX());
		assertEquals(4, firstLineSegment.getProjectionY());
		assertEquals(5, firstLineSegment.getLength());
	}

	@Test
	public void testCalculationNull() {

		assertNotNull(secondLineSegment);
		assertEquals(0, secondLineSegment.getProjectionX());
		assertEquals(0, secondLineSegment.getProjectionY());
		assertEquals(0, secondLineSegment.getLength());
	}

	@Test
	public void postTestCalculation() {

		assertNotNull(thirdLineSegment);
		assertEquals(3, thirdLineSegment.getProjectionX());
		assertEquals(4, thirdLineSegment.getProjectionY());
		assertEquals(5, thirdLineSegment.getLength());
	}
	
	@Test
	public void postStreamsTestCalculation() {

		List<LineSegment> lineSegment = new ArrayList<>();
		lineSegment.add(firstLineSegment);
		lineSegment.add(fourthLineSegment);
		assertEquals(3, service.maxFirstX(lineSegment));
		assertEquals(0, service.minFirstX(lineSegment));
		assertEquals(6, service.maxFirstY(lineSegment));
		assertEquals(4, service.minFirstY(lineSegment));
		assertEquals(7.5, service.averageLength(lineSegment));
		assertEquals(10, service.maxLength(lineSegment));
		assertEquals(5, service.minLength(lineSegment));
	}
	
	@Test
	public void dataBaseServiceTest() {
		LineSegmentEntity lineSegmentEntity = new LineSegmentEntity(3, 4, 6, 8, 3, 4, 5);
		assertEquals(3, lineSegmentEntity.getFirstX());
		assertEquals(4, lineSegmentEntity.getFirstY());
		assertEquals(6, lineSegmentEntity.getSecondX());
		assertEquals(8, lineSegmentEntity.getSecondY());
		assertEquals(3, lineSegmentEntity.getProjectionX());
		assertEquals(4, lineSegmentEntity.getProjectionY());
		assertEquals(5, lineSegmentEntity.getLength());
		ValidationError validationError = new ValidationError();
		validationError.addErrorMessages("message");
		ErrorEntity errorEntity = new ErrorEntity("3", "4000", "6", "8", validationError.getErrorMessages().get(0));
		assertEquals("3", errorEntity.getFirstX());
		assertEquals("4000", errorEntity.getFirstY());
		assertEquals("6", errorEntity.getSecondX());
		assertEquals("8", errorEntity.getSecondY());
		assertEquals("message", errorEntity.getMessage());
	}
}
