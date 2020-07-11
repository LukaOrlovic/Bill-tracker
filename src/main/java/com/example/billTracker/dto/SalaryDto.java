package com.example.billTracker.dto;

public class SalaryDto{

	private int salaryId;
	private EmployeeDto employee;
	private double amountValue;
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
