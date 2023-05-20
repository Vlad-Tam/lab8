package com.lab.segmentCalculation.segmentCalculation.services;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class LaunchCounterService {
	private Integer synchronizedCount = 0;
	private Integer notSynchronizedCount = 0;
	private AtomicInteger atomicCount = new AtomicInteger(0);
	
	public synchronized void incrementSynchronizedCount() {
		synchronizedCount++;
	}
	
	public void incrementNotSynchronizedCount() {
		notSynchronizedCount++;
	}
	
	public void incrementAtomicCount() {
		atomicCount.incrementAndGet();
	}
	
	public Integer getNotSynchronizedCount() {
		return notSynchronizedCount;
	}

	public Integer getSynchronizedCount() {
		return synchronizedCount;
	}

	public AtomicInteger getAtomicCount() {
		return atomicCount;
	}
	
	public void setCountNull() {
		notSynchronizedCount = 0;
		synchronizedCount = 0;
		atomicCount.set(0);		
	}


}
