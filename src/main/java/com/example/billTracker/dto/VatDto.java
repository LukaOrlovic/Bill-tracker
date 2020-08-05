package com.example.billTracker.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "vat")
public class VatDto{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int vatId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentCompanyId")
	@NotNull
	private CompanyDto company;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId")
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

	public VatDto(@NotNull int vatId, @NotNull CompanyDto company, @NotNull CountryDto country,
			@NotEmpty(message = "Amount cannot be empty") @Positive double amountValue, @NotEmpty String currency){
		super();
		this.vatId = vatId;
		this.company = company;
		this.country = country;
		this.amountValue = amountValue;
		this.currency = currency;
	}

	public int getVatId(){
		return vatId;
	}
	public void setVatId(int vatId){
		this.vatId = vatId;
	}
	public CompanyDto getCompany(){
		return company;
	}

	public void setCompany(CompanyDto company){
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
