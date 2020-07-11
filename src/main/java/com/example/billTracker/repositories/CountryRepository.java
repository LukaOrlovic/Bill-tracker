package com.example.billTracker.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.billTracker.dto.CountryDto;

public interface CountryRepository extends CrudRepository<CountryDto, Integer>{

	@Modifying
	@Transactional
	@Query("update CountryDto c set c.name = ?1 where c.countryId = ?2")
	void updateCountry(String name, int countryId);
}
