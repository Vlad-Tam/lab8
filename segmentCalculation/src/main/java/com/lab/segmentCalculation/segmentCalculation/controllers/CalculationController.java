package com.lab.segmentCalculation.segmentCalculation.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.PostParams;
import com.lab.segmentCalculation.segmentCalculation.classes.PostResponse;
import com.lab.segmentCalculation.segmentCalculation.classes.Response;
import com.lab.segmentCalculation.segmentCalculation.inMemoryCache.InMemoryCache;
import com.lab.segmentCalculation.segmentCalculation.services.CalculationService;
import com.lab.segmentCalculation.segmentCalculation.services.DataBaseService;
import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;
import com.lab.segmentCalculation.segmentCalculation.validators.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/lab")
public class CalculationController {
	private static final Logger logger = LogManager.getLogger(CalculationController.class);

	private CalculationService calculationService;
	private DataBaseService dataBaseService;
	private Validator validator;
	private InMemoryCache inMemoryCache;

	@Autowired
	public CalculationController(final CalculationService calculationService, DataBaseService dataBaseService, Validator validator,
			InMemoryCache inMemoryCache) {
		this.calculationService = calculationService;
		this.dataBaseService = dataBaseService;
		this.validator = validator;
		this.inMemoryCache = inMemoryCache;
	}

	@GetMapping("/firstController")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Response> calculationController(@RequestParam String firstX, @RequestParam String firstY,
			@RequestParam String secondX, @RequestParam String secondY) {

		Response testResponse = new Response();
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
			validationErrorResponse.setErrorStatus(HttpStatus.BAD_REQUEST.name());
			dataBaseService.addError(firstX, firstY, secondX, secondY, validationErrorResponse);
			testResponse.setValidationError(validationErrorResponse);
			return new ResponseEntity<>(testResponse, HttpStatus.BAD_REQUEST);
		}
		logger.info("Success validation");

		try {
			LineSegment lineSegment = calculationService.calculation(Integer.parseInt(firstX), Integer.parseInt(firstY),
					Integer.parseInt(secondX), Integer.parseInt(secondY));
			if (lineSegment.getLength() == 0) {
				logger.error("Length is not found. Points must be different");
				validationErrorResponse.addErrorMessages("Length is not found. Points must be different");
				validationErrorResponse.setErrorStatus(HttpStatus.NOT_FOUND.name());
				testResponse.setValidationError(validationErrorResponse);
				return new ResponseEntity<>(testResponse, HttpStatus.NOT_FOUND);
			}
			logger.info("Success performance");
			testResponse.setLineSegment(lineSegment);
			inMemoryCache.addLineSegment(lineSegment);
			dataBaseService.addLineSegment(lineSegment);
			return new ResponseEntity<>(testResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Internal Server Error happend");
			validationErrorResponse.addErrorMessages("Internal Server Error happend");
			validationErrorResponse.setErrorStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
			testResponse.setValidationError(validationErrorResponse);
			return new ResponseEntity<>(testResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/postController")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PostResponse> postController(@RequestBody List<PostParams> params) {
		
		PostResponse postResponse = new PostResponse();
		List<LineSegment> lineSegmentArray = new ArrayList<>();
		
		params.forEach((elem) -> {
			
			ValidationError validationErrorResponse = new ValidationError();
			validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(elem.getFirstX(), "firstX").getErrorMessages());
			validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(elem.getFirstY(), "firstY").getErrorMessages());
			validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(elem.getSecondX(), "secondX").getErrorMessages());
			validationErrorResponse.getErrorMessages()
				.addAll(validator.validateParameter(elem.getSecondY(), "secondY").getErrorMessages());
			if (validationErrorResponse.getErrorMessages().size() != 0) {
				logger.info("Validation error");
				validationErrorResponse.setErrorStatus(HttpStatus.BAD_REQUEST.name());
				dataBaseService.addError(elem.getFirstX(), elem.getFirstY(), elem.getSecondX(), elem.getSecondY(), validationErrorResponse);
				postResponse.addResponseList(new Response(validationErrorResponse));
			}else{
				logger.info("Success validation");
				LineSegment lineSegment = calculationService.postLineSegment(elem);
				inMemoryCache.addLineSegment(lineSegment);
				dataBaseService.addLineSegment(lineSegment);
				if (lineSegment.getLength() == 0) {
					logger.info("Length is not found. Points must be different");
					validationErrorResponse.addErrorMessages("Length is not found. Points must be different");
					validationErrorResponse.setErrorStatus(HttpStatus.NOT_FOUND.name());
					postResponse.addResponseList(new Response(validationErrorResponse));
				}else {
					logger.info("Success performance");
					postResponse.addResponseList(new Response(lineSegment));
					lineSegmentArray.add(lineSegment);
				}
			}
		});
		
		postResponse.setMinFirstX(calculationService.minFirstX(lineSegmentArray));
		postResponse.setMaxFirstX(calculationService.maxFirstX(lineSegmentArray));
		postResponse.setMinFirstY(calculationService.minFirstY(lineSegmentArray));
		postResponse.setMaxFirstY(calculationService.maxFirstY(lineSegmentArray));
		
		postResponse.setMaxLength(calculationService.maxLength(lineSegmentArray));
		postResponse.setMinLength(calculationService.minLength(lineSegmentArray));
		postResponse.setAverageLength(calculationService.averageLength(lineSegmentArray));
		
		return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
	}
}