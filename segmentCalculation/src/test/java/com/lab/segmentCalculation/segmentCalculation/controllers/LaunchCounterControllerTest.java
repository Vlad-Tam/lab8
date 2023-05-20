package com.lab.segmentCalculation.segmentCalculation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.lab.segmentCalculation.segmentCalculation.classes.LaunchResponce;
import com.lab.segmentCalculation.segmentCalculation.services.CalculationService;
import com.lab.segmentCalculation.segmentCalculation.services.LaunchCounterService;

public class LaunchCounterControllerTest {

	private LaunchCounterService launchCounterService = new LaunchCounterService();
	private LaunchCounterController testController = new LaunchCounterController(launchCounterService);
	private CalculationService calculationService = new CalculationService(launchCounterService);

	@Test
	public void launchCounterControllerTest() {

		ResponseEntity<LaunchResponce> firstResult = testController.launchCounterController();
		assertEquals(0, firstResult.getBody().getSynchronizedCount());
		assertEquals(0, firstResult.getBody().getNotSynchronizedCount());
		assertEquals(0, firstResult.getBody().getAtomicCount().get());

		calculationService.calculation(1, 2, 3, 4);

		ResponseEntity<LaunchResponce> secondResult = testController.launchCounterController();
		assertEquals(1, secondResult.getBody().getSynchronizedCount());
		assertEquals(1, secondResult.getBody().getNotSynchronizedCount());
		assertEquals(1, secondResult.getBody().getAtomicCount().get());
	}
	
	@Test
	public void сounterNullControllerTest() {

		ResponseEntity<LaunchResponce> firstResult = testController.сounterNullController();
		assertEquals(0, firstResult.getBody().getSynchronizedCount());
		assertEquals(0, firstResult.getBody().getNotSynchronizedCount());

		calculationService.calculation(1, 2, 3, 4);

		ResponseEntity<LaunchResponce> secondResult = testController.сounterNullController();
		assertEquals(0, secondResult.getBody().getSynchronizedCount());
		assertEquals(0, secondResult.getBody().getNotSynchronizedCount());
	}
}
