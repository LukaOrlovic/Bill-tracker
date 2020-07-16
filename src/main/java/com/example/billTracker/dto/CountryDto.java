package com.example.billTracker.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Country")
public class CountryDto{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int countryId;
	@NotEmpty
	private String name;
	
	public CountryDto(){
		// TODO Auto-generated constructor stub
	}

	public CountryDto(int countryId, String name){
		this.countryId = countryId;
		this.name = name;
	}

	public int getCountryId(){
		return countryId;
	}

	public void setCountryId(int countryId){
		this.countryId = countryId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	
}
