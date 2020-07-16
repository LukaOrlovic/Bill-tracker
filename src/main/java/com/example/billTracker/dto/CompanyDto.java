package com.example.billTracker.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "company")
public class CompanyDto{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int companyId;
	@NotEmpty
	private String companyName;
	private boolean parentCompany;

	public CompanyDto(int companyId, String companyName, boolean parentCompany){
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.parentCompany = parentCompany;
	}

	public CompanyDto(){
		// TODO Auto-generated constructor stub
	}

	public boolean isParentCompany(){
		return parentCompany;
	}

	public void setParentCompany(boolean parentCompany){
		this.parentCompany = parentCompany;
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
