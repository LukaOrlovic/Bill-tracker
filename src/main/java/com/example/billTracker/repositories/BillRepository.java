package com.example.billTracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.billTracker.dto.BillDto;

public interface BillRepository extends CrudRepository<BillDto, Integer>{
	
	List<BillDto> findBillsByAmountValueBetween(Double from, Double to);
	
	List<BillDto> findBillsByPayingCompanyCompanyIdIsAndReceivingCompanyCompanyIdIs(int payingCompanyId, int receivingCompanyId);
	
}
