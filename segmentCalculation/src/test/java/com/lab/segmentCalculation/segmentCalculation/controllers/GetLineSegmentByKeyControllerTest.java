package com.lab.segmentCalculation.segmentCalculation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.Point;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;

public class GetLineSegmentByKeyControllerTest {

	private InMemoryCache inMemoryCache = new InMemoryCache();
	private GetLineSegmentByKeyController testController = new GetLineSegmentByKeyController(inMemoryCache);
	
	@Test
	public void getLineSegmentByKeyControllerTest() {
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
		String key = firstX + " " + firstY + " " + secondX + " " + secondY; 
		ResponseEntity<LineSegment> result = testController.getLineSegmentByKeyController(key);
		assertEquals(inMemoryCache.getLineSegment(key), result.getBody());
	}
}
