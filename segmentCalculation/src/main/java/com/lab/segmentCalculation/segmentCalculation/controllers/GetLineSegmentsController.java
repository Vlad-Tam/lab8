package com.lab.segmentCalculation.segmentCalculation.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;
import com.lab.segmentCalculation.segmentCalculation.jpa.ErrorEntity;
import com.lab.segmentCalculation.segmentCalculation.jpa.LineSegmentEntity;
import com.lab.segmentCalculation.segmentCalculation.services.DataBaseService;

@RestController
@RequestMapping("/api/lab")
public class GetLineSegmentsController {
	private static final Logger logger = LogManager.getLogger(GetLineSegmentsController.class);
	private InMemoryCache inMemoryCache;
	private DataBaseService dataBaseService;
	
	@Autowired
	public GetLineSegmentsController(DataBaseService dataBaseService, InMemoryCache inMemoryCache) {
		this.dataBaseService = dataBaseService;
		this.inMemoryCache = inMemoryCache;
	}
	
	
	@GetMapping("/getLineSegments")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LineSegment>> getLineSegmentsController() {
		logger.info("Start");
		return ResponseEntity.ok(inMemoryCache.getAllLineSegments());
	}

	@GetMapping("/getDataBaseLineSegments")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LineSegmentEntity>> getDataBaseLineSegmentsController() {
		logger.info("Start get line segments database controller");
		return ResponseEntity.ok(dataBaseService.getAllLineSegments());
	}
	
	@GetMapping("/getDataBaseErrors")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ErrorEntity>> getDataBaseErrorsController() {
		logger.info("Start get errors database controller");
		return ResponseEntity.ok(dataBaseService.getAllErrors());
	}
}
