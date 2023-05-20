package com.lab.segmentCalculation.segmentCalculation.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;

@RestController
@RequestMapping("/api/lab")
public class GetLineSegmentByKeyController {
	private static final Logger logger = LogManager.getLogger(GetLineSegmentByKeyController.class);
	private InMemoryCache inMemoryCache;
	
	@Autowired
	public GetLineSegmentByKeyController(InMemoryCache inMemoryCache) {
		this.inMemoryCache = inMemoryCache;
	}
	
	@GetMapping("/getLineSegmentByKey")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LineSegment> getLineSegmentByKeyController(@RequestParam String key) {
		logger.info("Start");
		return ResponseEntity.ok(inMemoryCache.getLineSegment(key));
	}
}
