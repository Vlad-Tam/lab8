package com.lab.segmentCalculation.segmentCalculation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab.segmentCalculation.segmentCalculation.classes.LaunchResponce;
import com.lab.segmentCalculation.segmentCalculation.services.LaunchCounterService;

@RestController
@RequestMapping("/api/lab")
public class LaunchCounterController {

private LaunchCounterService launchCounterService;
	
	@Autowired
	public LaunchCounterController(LaunchCounterService launchCounterService) {
		this.launchCounterService = launchCounterService;
	}
	
	@GetMapping("/launchCounter")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LaunchResponce> launchCounterController(){
		
		LaunchResponce launchResponce = new LaunchResponce();
		launchResponce.setNotSynchronizedCount(launchCounterService.getNotSynchronizedCount());
		launchResponce.setSynchronizedCount(launchCounterService.getSynchronizedCount());
		launchResponce.setAtomicCount(launchCounterService.getAtomicCount());
		return ResponseEntity.ok(launchResponce);
	}
	
	@GetMapping("/nullLaunchCounter")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LaunchResponce> сounterNullController(){
		
		LaunchResponce launchResponce = new LaunchResponce();
		launchCounterService.setCountNull();
		launchResponce.setSynchronizedCount(launchCounterService.getSynchronizedCount());
		launchResponce.setNotSynchronizedCount(launchCounterService.getNotSynchronizedCount());
		launchResponce.setAtomicCount(launchCounterService.getAtomicCount());
		return ResponseEntity.ok(launchResponce);
	}
}
