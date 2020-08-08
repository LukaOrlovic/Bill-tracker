package com.example.billTracker.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "employee")
public class EmployeeDto{
	
	@Id
	private int employeeId;
	private String name;
	private String surname;
	private RoleDto position;
	private String username;
	private String password;
	
	public EmployeeDto(){
		
	}	

	public EmployeeDto(int employeeId, String name, String surname, RoleDto position, String username, String password){
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.username = username;
		this.password = password;
	}

	public RoleDto getPosition(){
		return position;
	}

	public void setPosition(RoleDto position){
		this.position = position;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
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

	
}
