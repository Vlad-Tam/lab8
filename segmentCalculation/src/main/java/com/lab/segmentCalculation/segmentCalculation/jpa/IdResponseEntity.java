package com.lab.segmentCalculation.segmentCalculation.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "id_table")
public class IdResponseEntity {
	
	@Id
	@Column(name = "program_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
//	public IdResponseEntity(Integer id) {
//		this.id = id;
//	}
	
	//public IdResponseEntity() {}
}
