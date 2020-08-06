package com.example.billTracker.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "salary")
public class SalaryDto{

	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int salaryId;
	
	@Field(type = FieldType.Nested, includeInParent = true)
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "employeeId")
	@NotNull
	private EmployeeDto employee;
	
	@NotEmpty(message = "Amount cannot be empty")
	@Positive
	private double amountValue;
	
	@NotEmpty
	private String currency;
	
	public SalaryDto(){
		// TODO Auto-generated constructor stub
	}

	public SalaryDto(int salaryId, EmployeeDto employee, int amountValue, String currency){
		super();
		this.salaryId = salaryId;
		this.employee = employee;
		this.amountValue = amountValue;
		this.currency = currency;
	}

	public int getSalaryId(){
		return salaryId;
	}

	public void setSalaryId(int salaryId){
		this.salaryId = salaryId;
	}

	public EmployeeDto getEmployee(){
		return employee;
	}

	public void setEmployee(EmployeeDto employee){
		this.employee = employee;
	}

	public String getCurrency(){
		return currency;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public double getAmountValue(){
		return amountValue;
	}

	public void setAmountValue(double amountValue){
		this.amountValue = amountValue;
	}
	
}
