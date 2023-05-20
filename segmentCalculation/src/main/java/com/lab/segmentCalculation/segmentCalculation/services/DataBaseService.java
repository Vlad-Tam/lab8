package com.lab.segmentCalculation.segmentCalculation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.jpa.LineSegmentRepository;
import com.lab.segmentCalculation.segmentCalculation.validators.ValidationError;
import com.lab.segmentCalculation.segmentCalculation.jpa.ErrorRepository;
import com.lab.segmentCalculation.segmentCalculation.jpa.LineSegmentEntity;
import com.lab.segmentCalculation.segmentCalculation.jpa.ErrorEntity;

@Service
public class DataBaseService {
	@Autowired
	private LineSegmentRepository lineSegmRep;
	@Autowired
	private ErrorRepository errRep;
	
	public void addLineSegment(LineSegment lineSegment) {
		LineSegmentEntity entity = new LineSegmentEntity(lineSegment.getFirstPoint().getX(), lineSegment.getFirstPoint().getY(), lineSegment.getSecondPoint().getX(), lineSegment.getSecondPoint().getY(), lineSegment.getProjectionX(), lineSegment.getProjectionY(), lineSegment.getLength());
		lineSegmRep.save(entity);
	}
	
	public List<LineSegmentEntity> getAllLineSegments(){
		return lineSegmRep.findAll();
	}
	
	public void addError(String firstX, String firstY, String secondX, String secondY,ValidationError validationError) {
		ErrorEntity entity = new ErrorEntity(firstX, firstY, secondX, secondY, validationError.getErrorMessages().get(validationError.getErrorMessages().size() - 1));
		errRep.save(entity);
	}
	
	public List<ErrorEntity> getAllErrors(){
		return errRep.findAll();
	}
		
}
