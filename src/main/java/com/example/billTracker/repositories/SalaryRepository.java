package com.example.billTracker.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.billTracker.dto.SalaryDto;

public interface SalaryRepository extends ElasticsearchRepository<SalaryDto, Integer>{

	List<SalaryDto> findByAmountValue(double amountValue);
	
	List<SalaryDto> findByOrderBySalaryIdAsc();
	
	List<SalaryDto> findSalariesByAmountValueBetween(Double from, Double to);
	
	SalaryDto findByEmployeeEmployeeId(int employeeId);

}
