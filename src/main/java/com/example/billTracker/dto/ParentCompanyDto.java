package com.example.billTracker.dto;

import java.util.List;

public class ParentCompanyDto extends CompanyDto{

	private List<EmployeeDto> employees;

	public List<EmployeeDto> getEmployees(){
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees){
		this.employees = employees;
	}
	
}
