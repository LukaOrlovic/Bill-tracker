package com.example.billTracker.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.billTracker.dto.VatDto;

public interface VatRepository extends CrudRepository<VatDto, Integer>{

}
