package com.example.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.entity.States;


public interface StatesRepository extends JpaRepository<States, Integer>{
	
	//@Query("select * from States where countryId=:countryId")
	//@Query(" from States where countryId=:countryId")
	public List<States> findByCountryId(Integer countryId);

}
