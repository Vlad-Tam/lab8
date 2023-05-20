package com.lab.segmentCalculation.segmentCalculation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.classes.Point;
import com.lab.segmentCalculation.segmentCalculation.classes.PostParams;

@Service
public class CalculationService {

	private LaunchCounterService launchCounterService;

	@Autowired
	public CalculationService(LaunchCounterService counterService) {
		this.launchCounterService = counterService;
	}

	public LineSegment calculation(int firstX, int firstY, int secondX, int secondY) {

		launchCounterService.incrementNotSynchronizedCount();
		launchCounterService.incrementSynchronizedCount();
		launchCounterService.incrementAtomicCount();

		Point point1 = new Point(firstX, firstY);
		Point point2 = new Point(secondX, secondY);
		LineSegment lineSegment = new LineSegment(point1, point2);

		lineSegment.setProjectionX(projectionCalculation(point1.getX(), point2.getX()));
		lineSegment.setProjectionY(projectionCalculation(point1.getY(), point2.getY()));
		lineSegment.setLength(lengthCalculation(lineSegment.getProjectionX(), lineSegment.getProjectionY()));

		return lineSegment;
	}

	public int projectionCalculation(int first, int second) {
		return Math.absExact(first - second);
	}

	public Double lengthCalculation(int projectionX, int projectionY) {
		return Math.sqrt(Math.pow(projectionX, 2) + Math.pow(projectionY, 2));
	}

	public LineSegment postLineSegment(PostParams params) {
		
		launchCounterService.incrementNotSynchronizedCount();
		launchCounterService.incrementSynchronizedCount();
		launchCounterService.incrementAtomicCount();
		
		Point point1 = new Point(Integer.parseInt(params.getFirstX()), Integer.parseInt(params.getFirstY()));
		Point point2 = new Point(Integer.parseInt(params.getSecondX()), Integer.parseInt(params.getSecondY()));
		LineSegment lineSegment = new LineSegment(point1, point2);		
		lineSegment.setProjectionX(projectionCalculation(Integer.parseInt(params.getFirstX()), Integer.parseInt(params.getSecondX())));
		lineSegment.setProjectionY(projectionCalculation(Integer.parseInt(params.getFirstY()), Integer.parseInt(params.getSecondY())));
		lineSegment.setLength(lengthCalculation(lineSegment.getProjectionX(), lineSegment.getProjectionY()));
		return lineSegment;
	}

	public int minFirstX(List<LineSegment> lineSegment) {
		return lineSegment.stream().mapToInt(u ->{return u.getFirstPoint().getX();}).min().getAsInt();
	}
	
	public int maxFirstX(List<LineSegment> lineSegment) {
		return lineSegment.stream().mapToInt(u ->{return u.getFirstPoint().getX();}).max().getAsInt();
	}
	
	public int minFirstY(List<LineSegment> lineSegment) {
		return lineSegment.stream().mapToInt(u ->{return u.getFirstPoint().getY();}).min().getAsInt();
	}
	
	public int maxFirstY(List<LineSegment> lineSegment) {
		return lineSegment.stream().mapToInt(u ->{return u.getFirstPoint().getY();}).max().getAsInt();
	}
	
	public Double minLength(List<LineSegment> lineSegment) {
		return lineSegment.stream().mapToDouble(LineSegment::getLength).min().getAsDouble();
	}
	
	public Double maxLength(List<LineSegment> lineSegment) {
		return lineSegment.stream().mapToDouble(LineSegment::getLength).max().getAsDouble();
	}
	
	public Double averageLength(List<LineSegment> lineSegment) {
		return lineSegment.stream().mapToDouble(LineSegment::getLength).average().getAsDouble();
	}
}
