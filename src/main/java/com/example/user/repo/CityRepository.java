package com.example.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.user.entity.Cities;

public interface CityRepository extends JpaRepository<Cities, Integer>{
	
	//@Query("select * from Cities where countryId=:countryId")
	@Query(" from v where stateId=:stateId")
	//select * from cities where countryId=?;
	public List<Cities> findByStateId(Integer stateId);

}
