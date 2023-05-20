package com.lab.segmentCalculation.segmentCalculation.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LineSegment {
	private Point firstPoint;
	private Point secondPoint;
	private int projectionX;
	private int projectionY;
	private Double length;

	public LineSegment() {}
	
	public LineSegment(Point firstPoint, Point secondPoint) {
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
	}

	public int getProjectionX() {
		return projectionX;
	}

	public void setProjectionX(int projectionX) {
		this.projectionX = projectionX;
	}

	public Point getSecondPoint() {
		return secondPoint;
	}

	public int getProjectionY() {
		return projectionY;
	}

	public void setProjectionY(int projectionY) {
		this.projectionY = projectionY;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Point getFirstPoint() {
		return firstPoint;
	}

	@JsonIgnore
	public String getKey() {
		return this.firstPoint.getX() + " " + this.firstPoint.getY() + " " + 
				this.secondPoint.getX() + " " + this.secondPoint.getY();
	}
}
