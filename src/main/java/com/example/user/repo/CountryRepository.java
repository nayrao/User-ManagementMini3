package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
