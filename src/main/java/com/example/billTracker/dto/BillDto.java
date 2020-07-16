package com.example.billTracker.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillDto{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int billId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "payingCompanyId")
	private CompanyDto payingCompany;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receivingCompanyId")
	private CompanyDto receivingCompany;
	
	private double amountValue;
	private String currency;

	public BillDto(){
		// TODO Auto-generated constructor stub
	}

	public BillDto(int billId, CompanyDto payingCompany, CompanyDto receivingCompany, double amountValue, String currency){
		this.billId = billId;
		this.payingCompany = payingCompany;
		this.receivingCompany = receivingCompany;
		this.amountValue = amountValue;
		this.currency = currency;
	}

	public int getBillId(){
		return billId;
	}

	public void setBillId(int billId){
		this.billId = billId;
	}

	public CompanyDto getPayingCompany(){
		return payingCompany;
	}

	public void setPayingCompany(CompanyDto payingCompany){
		this.payingCompany = payingCompany;
	}

	public CompanyDto getReceivingCompany(){
		return receivingCompany;
	}

	public void setReceivingCompany(CompanyDto receivingCompany){
		this.receivingCompany = receivingCompany;
	}

	public double getAmountValue(){
		return amountValue;
	}

	public void setAmountValue(double amountValue){
		this.amountValue = amountValue;
	}

	public String getCurrency(){
		return currency;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

}
