package com.example.billTracker.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.billTracker.dto.CompanyDto;

public interface CompanyRepository extends CrudRepository<CompanyDto, Integer>{

	@Modifying
	@Transactional
	@Query("update CompanyDto c set c.companyName = ?1 where c.companyId = ?2")
	void updateCompany(String companyName, int companyId);
}
