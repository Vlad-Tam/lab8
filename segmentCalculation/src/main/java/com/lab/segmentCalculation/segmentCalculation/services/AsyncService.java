package com.lab.segmentCalculation.segmentCalculation.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;
import com.lab.segmentCalculation.segmentCalculation.jpa.AsyncEntity;
import com.lab.segmentCalculation.segmentCalculation.jpa.AsyncRepository;
import com.lab.segmentCalculation.segmentCalculation.jpa.IdResponseEntity;
import com.lab.segmentCalculation.segmentCalculation.jpa.IdResponseRepository;

@Service
public class AsyncService {

	@Autowired
	private AsyncRepository asyncRep;
	@Autowired
	private IdResponseRepository idRep;

	public void addAsyncLineSegment(LineSegment lineSegment) {
		AsyncEntity entity = new AsyncEntity(lineSegment.getFirstPoint().getX(), lineSegment.getFirstPoint().getY(),
				lineSegment.getSecondPoint().getX(), lineSegment.getSecondPoint().getY(), lineSegment.getProjectionX(),
				lineSegment.getProjectionY(), lineSegment.getLength());
		
		List<IdResponseEntity> idList = getIdRespRep();	
		IdResponseEntity currId = idList.get(idList.size() - 1);
		
		entity.setProgram_id(currId.getId());
		asyncRep.save(entity);
	}
	
	public List<AsyncEntity> getAsyncAllLineSegments() {
		return asyncRep.findAll();
	}

	public void addIdRespRep(IdResponseEntity idResponseEntity) {
		IdResponseEntity entity = new IdResponseEntity();
		entity.setId(idResponseEntity.getId());
		idRep.save(entity);
	}

	public List<IdResponseEntity> getIdRespRep() {
		return idRep.findAll();
		
	}
	
	public IdResponseEntity gelLastId() {
		List<IdResponseEntity> idList = getIdRespRep();	
		return  idList.get(idList.size() - 1);
		
	}
}