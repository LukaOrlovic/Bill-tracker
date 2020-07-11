package com.example.billTracker.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.billTracker.dto.BillDto;

public interface BillRepository extends CrudRepository<BillDto, Integer>{

}
