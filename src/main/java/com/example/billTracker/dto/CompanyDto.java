package com.example.billTracker.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class CompanyDto{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int companyId;
	private String companyName;

	public CompanyDto(int companyId, String name){
		this.companyId = companyId;
		this.companyName = name;
	}
	
	public CompanyDto(){
		// TODO Auto-generated constructor stub
	}

	public int getCompanyId(){
		return companyId;
	}

	public void setCompanyId(int companyId){
		this.companyId = companyId;
	}
	
	public String getCompanyName(){
		return companyName;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	
}
