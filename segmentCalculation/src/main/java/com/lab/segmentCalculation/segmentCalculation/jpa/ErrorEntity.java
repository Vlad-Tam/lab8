package com.lab.segmentCalculation.segmentCalculation.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "errors")
public class ErrorEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "firstX")
	private String firstX;
	
	@Column(name = "firstY")
	private String firstY;
	
	@Column(name = "secondX")
	private String secondX;
	
	@Column(name = "secondY")
	private String secondY;
	
	@Column(name = "message")
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstX() {
		return firstX;
	}

	public void setFirstX(String firstX) {
		this.firstX = firstX;
	}

	public String getFirstY() {
		return firstY;
	}

	public void setFirstY(String firstY) {
		this.firstY = firstY;
	}

	public String getSecondX() {
		return secondX;
	}

	public void setSecondX(String secondX) {
		this.secondX = secondX;
	}

	public String getSecondY() {
		return secondY;
	}

	public void setSecondY(String secondY) {
		this.secondY = secondY;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ErrorEntity(String firstX, String firstY, String secondX, String secondY, String message) {
		this.firstX = firstX;
		this.firstY = firstY;
		this.secondX = secondX;
		this.secondY = secondY;
		this.message = message;
	}
	public ErrorEntity() {}
}

