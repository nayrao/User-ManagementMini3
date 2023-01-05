package com.example.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tbl_States")
@Entity
@Data
public class States {
	@Id
	private Integer stateId;
	private String stateName;
	private Integer countryId;

}
