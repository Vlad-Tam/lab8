package com.lab.segmentCalculation.segmentCalculation.classes;

import java.util.concurrent.atomic.AtomicInteger;

public class LaunchResponce {
	private Integer notSynchronizedCount = 0;
	private Integer synchronizedCount = 0;	
	private AtomicInteger atomicCount = new AtomicInteger(0);
	
	public Integer getSynchronizedCount() {
		return synchronizedCount;
	}
	
	public void setSynchronizedCount(Integer synchronizedCount) {
		this.synchronizedCount = synchronizedCount;
	}
	
	public Integer getNotSynchronizedCount() {
		return notSynchronizedCount;
	}
	
	public void setNotSynchronizedCount(Integer notSynchronizedCount) {
		this.notSynchronizedCount = notSynchronizedCount;
	}

	public AtomicInteger getAtomicCount() {
		return atomicCount;
	}

	public void setAtomicCount(AtomicInteger atomicCount) {
		this.atomicCount = atomicCount;
	}	
}
