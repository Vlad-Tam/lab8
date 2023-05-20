package com.lab.segmentCalculation.segmentCalculation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.Point;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;
import com.lab.segmentCalculation.segmentCalculation.services.DataBaseService;

public class GetLineSegmentsControllerTest {
	
	private InMemoryCache inMemoryCache = new InMemoryCache();
	private DataBaseService dataBaseService = new DataBaseService();
	private GetLineSegmentsController testController = new GetLineSegmentsController(dataBaseService, inMemoryCache);
	
	@Test
	public void getLineSegmentsControllerTest() {
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
		ResponseEntity<List<LineSegment>> result = testController.getLineSegmentsController();
		assertEquals(inMemoryCache.getAllLineSegments(), result.getBody());
	}
	
}
