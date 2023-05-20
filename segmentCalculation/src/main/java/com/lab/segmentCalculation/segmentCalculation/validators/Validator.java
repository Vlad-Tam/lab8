package com.lab.segmentCalculation.segmentCalculation.validators;

import org.springframework.stereotype.Component;

import com.lab.segmentCalculation.segmentCalculation.controllers.CalculationController;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class Validator {
	private static final Logger logger = LogManager.getLogger(CalculationController.class);

	public ValidationError validateParameter(String number, String name) {
		ValidationError response = new ValidationError();
		if (number.isEmpty()) {
			logger.error("Coordinate is Empty");
			response.addErrorMessages("Coordinate " + name + " is Empty");
		}
		if (!StringUtils.isNumeric(number)) {
			logger.error("Coordinate must be a number");
			response.addErrorMessages("Coordinate " + name + " must be a number");
		} else {
			if (number.length() > 3) {
				logger.error("Coordinate must be less then 1000");
				response.addErrorMessages("Coordinate " + name + " must be less then 1000");
			}
		}
		return response;
	}
	
}
