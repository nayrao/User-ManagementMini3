package com.example.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tbl_countries")
@Entity
@Data
public class Country {

	@Id
	private Integer countryID;
	private String countryName;
}
