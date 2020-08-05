package com.example.billTracker.config;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.billTracker.dto.EmployeeDto;
import com.example.billTracker.dto.SalaryDto;
import com.example.billTracker.repositories.EmployeeRepository;
import com.example.billTracker.repositories.SalaryRepository;

@Component
public class EmployeeListBean{

//	@Autowired
//	EmployeeRepository employeeRepository;
//
//	@Autowired
//	SalaryRepository salaryRepository;
//
//	public List<EmployeeDto> employeesWithoutSalary = getAllWithoutSalary();
//
//	private List<EmployeeDto> getAllWithoutSalary(){
//		
//		List<EmployeeDto> employeesWithoutSalaries = new LinkedList<>();
//
//		List<EmployeeDto> employees = (List<EmployeeDto>) employeeRepository.findAll();
//
//		List<SalaryDto> salaries = (List<SalaryDto>) salaryRepository.findAll();
//
//		for(EmployeeDto e : employees){
//			for(SalaryDto s : salaries){
//				if(s.getEmployee().getEmployeeId() == e.getEmployeeId()) {
//					break;
//				}
//				if(salaries.get(salaries.size()-1).equals(s)) {
//					employeesWithoutSalaries.add(e);
//				}
//			}
//		}
//		
//		return employeesWithoutSalaries;
//
//	}

}
