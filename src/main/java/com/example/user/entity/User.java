package com.example.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "tbl_Users")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String fname;
	private String lname;
	private String email;
	private Long phno;
	private Date dob;
	private String gender;
	private Integer countryID;
	private Integer stateId;
	private Integer cityId;
	private String userPwd;
	private String accStatus;
	
	

}
