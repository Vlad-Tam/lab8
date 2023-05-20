package com.lab.segmentCalculation.segmentCalculation.inMemoryCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lab.segmentCalculation.segmentCalculation.classes.LineSegment;

@Component
public class InMemoryCache {
	private Map<String, LineSegment> cache = new HashMap<String, LineSegment>();
	
	public void addLineSegment(LineSegment lineSegment) {
		cache.put(lineSegment.getKey(), lineSegment);
	}
	
	public LineSegment getLineSegment(String key) {
		return cache.get(key);
	}
	
	public Integer getCacheSize() {
		return cache.size();
	}
	
	public List<LineSegment> getAllLineSegments(){
		List<LineSegment> lineSegmentList = new ArrayList<LineSegment>();
		cache.forEach((k, v) -> lineSegmentList.add(v));
		return lineSegmentList;
	}
}
