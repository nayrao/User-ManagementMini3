package com.example.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = " tbl_Cities")
@Entity
@Data
public class Cities {

	@Id
	private Integer cityId;
	private String cityName;
	private Integer stateId;
}
