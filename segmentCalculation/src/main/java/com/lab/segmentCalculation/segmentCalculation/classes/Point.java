package com.lab.segmentCalculation.segmentCalculation.classes;

public class Point {
	private int coordinateX;
	private int coordinateY;

	public Point(int coordinateX, int coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}
	
	public int getX() {
		return this.coordinateX;
	}
	
	public int getY() {
		return this.coordinateY;
	}
}