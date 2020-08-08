package com.example.billTracker.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.billTracker.dto.EmployeeDto;

public interface EmployeeRepository extends ElasticsearchRepository<EmployeeDto, Integer>{

	List<EmployeeDto> findByEmployeeIdEquals(int employeeId);
	
	EmployeeDto findTopByOrderByEmployeeIdDesc(); 
	
	EmployeeDto findByUsername(String username);
	
}
