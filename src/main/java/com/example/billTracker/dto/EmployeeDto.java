package com.example.billTracker.dto;

public class EmployeeDto{

	private int employeeId;
	private String name;
	private String surname;
	private String position;
	
	public EmployeeDto(){
		
	}

	public EmployeeDto(int employeeId, String name, String surname, String poistion){
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.surname = surname;
		this.position = poistion;
	}

	public int getEmployeeId(){
		return employeeId;
	}

	public void setEmployeeId(int employeeId){
		this.employeeId = employeeId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSurname(){
		return surname;
	}

	public void setSurname(String surname){
		this.surname = surname;
	}

	public String getPoistion(){
		return position;
	}

	public void setPoistion(String poistion){
		this.position = poistion;
	}
	
	
}
