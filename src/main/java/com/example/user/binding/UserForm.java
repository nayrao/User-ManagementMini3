package com.example.user.binding;

import java.util.Date;

import lombok.Data;
@Data
public class UserForm {
	
	private String fname;
	private String lname;
	private String email;
	private Long phno;
	private Date dob;
	private String gender;
	private Integer countryID;
	private Integer stateId;
	private Integer cityId;

}
