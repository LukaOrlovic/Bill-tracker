package com.example.billTracker.dto;

public class VatDto{

	private int vatId;
	private ParentCompanyDto company;
	private CountryDto country;
	private double amountValue;
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
