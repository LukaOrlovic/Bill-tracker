package com.example.billTracker.dto;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class VatDto{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int vatId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeId")
	@NotNull
	private ParentCompanyDto company;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeId")
	@NotNull
	private CountryDto country;
	
	@NotEmpty(message = "Amount cannot be empty")
	@Positive
	private double amountValue;
	
	@NotEmpty
	private String currency;
	
	public VatDto(){
		// TODO Auto-generated constructor stub
	}
	
	public VatDto(int vatId, ParentCompanyDto company, CountryDto country, int value, String currency){
		super();
		this.vatId = vatId;
		this.company = company;
		this.country = country;
		this.amountValue = value;
		this.currency = currency;
	}

	public int getVatId(){
		return vatId;
	}
	public void setVatId(int vatId){
		this.vatId = vatId;
	}
	public ParentCompanyDto getCompany(){
		return company;
	}
	public void setCompany(ParentCompanyDto company){
		this.company = company;
	}
	public CountryDto getCountry(){
		return country;
	}
	public void setCountry(CountryDto country){
		this.country = country;
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
