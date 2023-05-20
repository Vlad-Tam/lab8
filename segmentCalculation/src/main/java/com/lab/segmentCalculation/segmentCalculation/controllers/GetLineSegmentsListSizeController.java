package com.lab.segmentCalculation.segmentCalculation.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab.segmentCalculation.segmentCalculation.classes.IntResponce;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;

@RestController
@RequestMapping("/api/lab")
public class GetLineSegmentsListSizeController {
	private static final Logger logger = LogManager.getLogger(GetLineSegmentsListSizeController.class);
	private InMemoryCache inMemoryCache;
	
	@Autowired
	public GetLineSegmentsListSizeController(InMemoryCache inMemoryCache) {
		this.inMemoryCache = inMemoryCache;
	}
	
	@GetMapping("/getLineSegmentsListSize")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<IntResponce> getLineSegmentsListSizeController() {
		IntResponce responce = new IntResponce();
		responce.setSize(inMemoryCache.getCacheSize());
		logger.info("Success performance");
		return ResponseEntity.ok(responce);
	}
}
