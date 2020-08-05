package com.example.billTracker.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "employee")
public class EmployeeDto{

	@Id
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

	public String getPosition(){
		return position;
	}

	public void setPosition(String position){
		this.position = position;
	}
	
	
}
