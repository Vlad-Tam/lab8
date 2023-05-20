package com.lab.segmentCalculation.segmentCalculation.controllers;

import java.util.concurrent.CompletableFuture;

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

import com.lab.segmentCalculation.segmentCalculation.jpa.IdResponseEntity;
import com.lab.segmentCalculation.segmentCalculation.classes.AsyncResponse;
import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.services.AsyncService;
import com.lab.segmentCalculation.segmentCalculation.services.CalculationService;
import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;
import com.lab.segmentCalculation.segmentCalculation.validators.Validator;

@RestController
@RequestMapping("/api/lab")
public class AsyncController {

	private static final Logger logger = LogManager.getLogger(GetLineSegmentByKeyController.class);
	private Validator validator;
	private AsyncService asyncService;
	private CalculationService calculationService;

	@Autowired
	public AsyncController(CalculationService calculationService, Validator validator, AsyncService asyncService) {
		this.calculationService = calculationService;
		this.validator = validator;
		this.asyncService = asyncService;
	}

	@GetMapping("/asyncController")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AsyncResponse> calculationController(@RequestParam String firstX, @RequestParam String firstY,
			@RequestParam String secondX, @RequestParam String secondY) {
		logger.info("Start");
		AsyncResponse asyncResponse = new AsyncResponse();
		ValidationError validationErrorResponse = new ValidationError();

		validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(firstX, "firstX").getErrorMessages());
		validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(firstY, "firstY").getErrorMessages());
		validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(secondX, "secondX").getErrorMessages());
		validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(secondY, "secondY").getErrorMessages());
		
		if (validationErrorResponse.getErrorMessages().size() != 0) {
			logger.error("Validation error");
			asyncResponse.setMessage("Validation error");			
			return new ResponseEntity<>(asyncResponse, HttpStatus.BAD_REQUEST);
		}
		
		logger.info("Success validation");

		asyncService.addIdRespRep(new IdResponseEntity());
		IdResponseEntity id = asyncService.gelLastId();
				
		CompletableFuture.runAsync(new Runnable(){
			@Override
			public void run() {
				logger.info("Starting lineSegmentCalculation");
				LineSegment lineSegment = calculationService.calculation(Integer.parseInt(firstX), Integer.parseInt(firstY),
						Integer.parseInt(secondX), Integer.parseInt(secondY));
				asyncService.addAsyncLineSegment(lineSegment);
				logger.info("LineSegmentCalculation has been ended");
			}
		});	
		
		asyncResponse.setId(id.getId());
		asyncResponse.setMessage("Success performance");
		logger.info("End");		
		return new ResponseEntity<>(asyncResponse, HttpStatus.OK);
	}
}
