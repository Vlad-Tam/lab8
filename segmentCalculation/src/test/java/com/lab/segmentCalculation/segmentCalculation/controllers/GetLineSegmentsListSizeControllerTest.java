package com.lab.segmentCalculation.segmentCalculation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.lab.segmentCalculation.segmentCalculation.classes.IntResponce;
import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.Point;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;

public class GetLineSegmentsListSizeControllerTest {
	
	private InMemoryCache inMemoryCache = new InMemoryCache();
	private GetLineSegmentsListSizeController testController = new GetLineSegmentsListSizeController(inMemoryCache);
	
	@Test
	public void getLineSegmentsListSizeControllerTest() {
		String firstX = "3";
		String firstY = "4";
		String secondX = "6";
		String secondY = "8";
		Point point1 = new Point(Integer.parseInt(firstX), Integer.parseInt(firstY));
		Point point2 = new Point(Integer.parseInt(secondX), Integer.parseInt(secondY));
		LineSegment lineSegment = new LineSegment(point1, point2);
		lineSegment.setProjectionX(3);
		lineSegment.setProjectionY(4);
		lineSegment.setLength((double) 5);
		
		inMemoryCache.addLineSegment(lineSegment);
		ResponseEntity<IntResponce> result = testController.getLineSegmentsListSizeController();
		assertEquals(1, result.getBody().getSize());
		assertNull(result.getBody().getMessage());
	}
}
