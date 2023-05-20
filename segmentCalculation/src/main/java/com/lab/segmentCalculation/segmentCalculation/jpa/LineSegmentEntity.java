package com.lab.segmentCalculation.segmentCalculation.jpa;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "linesegments")						//linesegments
public class LineSegmentEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "firstX")
	private Integer firstX;
	
	@Column(name = "firstY")
	private Integer firstY;
	
	@Column(name = "secondX")
	private Integer secondX;
	
	@Column(name = "secondY")
	private Integer secondY;
	
	@Column(name = "projectionX")
	private Integer projectionX;
	
	@Column(name = "projectionY")
	private Integer projectionY;
	
	@Nonnull
	@Column(name = "length")
	private Double length;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstX() {
		return firstX;
	}

	public void setFirstX(Integer firstX) {
		this.firstX = firstX;
	}

	public Integer getFirstY() {
		return firstY;
	}

	public void setFirstY(Integer firstY) {
		this.firstY = firstY;
	}

	public Integer getSecondX() {
		return secondX;
	}

	public void setSecondX(Integer secondX) {
		this.secondX = secondX;
	}

	public Integer getSecondY() {
		return secondY;
	}

	public void setSecondY(Integer secondY) {
		this.secondY = secondY;
	}

	public Integer getProjectionX() {
		return projectionX;
	}

	public void setProjectionX(Integer projectionX) {
		this.projectionX = projectionX;
	}

	public Integer getProjectionY() {
		return projectionY;
	}

	public void setProjectionY(Integer projectionY) {
		this.projectionY = projectionY;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}
	
	public LineSegmentEntity(int firstX, int firstY, int secondX, int secondY, int projectionX, int projectionY, double length) {
		this.firstX = firstX;
		this.firstY = firstY;
		this.secondX = secondX;
		this.secondY = secondY;
		this.projectionX = projectionX;
		this.projectionY = projectionY;
		this.length = length;
	}
	public LineSegmentEntity() {}
	
}
