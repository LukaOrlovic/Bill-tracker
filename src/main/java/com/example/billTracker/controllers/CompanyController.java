package com.example.billTracker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.billTracker.dto.BillDto;
import com.example.billTracker.dto.CompanyDto;
import com.example.billTracker.helper.ErrorMessage;
import com.example.billTracker.repositories.BillRepository;
import com.example.billTracker.repositories.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController{

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	BillRepository billRepository;

	private CompanyDto editCompany;

	@GetMapping("/getAll")
	public String getAll(Model model){

		List<CompanyDto> companies = (List<CompanyDto>) companyRepository.findAll();

		model.addAttribute("companies", companies);

		if(!companies.isEmpty()) return "company/all";
			
		ErrorMessage errorMessage = new ErrorMessage("Could not find any companies saved.");
		
		model.addAttribute("errorObject", errorMessage);
		
		return "error";
	}

	@PostMapping("/editCompany")
	public String editCompany(@RequestParam("companyId") String companyId, Model model){

		Optional<CompanyDto> company = companyRepository.findById(Integer.parseInt(companyId));

		editCompany = company.get();

		model.addAttribute("company", company);

		if(company.isPresent())
			return "company/edit";
		
		ErrorMessage errorMessage = new ErrorMessage("Could not find the selected company.");
		
		model.addAttribute("errorObject", errorMessage);
		
		return "error";
	}

	@PostMapping("/edit")
	public String editCompany(@ModelAttribute("company") CompanyDto company){

		companyRepository.updateCompany(company.getCompanyName(), editCompany.getCompanyId());

		editCompany = null;

		return "redirect:/company/getAll";
	}

	@PostMapping("/delete")
	public String deleteCompany(@RequestParam("companyId") String companyId, Model model){

		Optional<CompanyDto> company = companyRepository.findById(Integer.parseInt(companyId));
		
		if(company.get() != null && company.get().isParentCompany()) {
			ErrorMessage errorMessage = new ErrorMessage("Could not delete a parent company.");
			
			model.addAttribute("errorObject", errorMessage);
			
			return "error";
		}else if(company.get() == null){
			ErrorMessage errorMessage = new ErrorMessage("Could not find the selected company.");
			
			model.addAttribute("errorObject", errorMessage);
			
			return "error";
		}
		
		int id = company.get().getCompanyId();
		
		List<BillDto> bills = billRepository.findBillsByPayingCompanyCompanyIdIsOrReceivingCompanyCompanyIdIs(id, id);
		
		if(!bills.isEmpty()) {
			ErrorMessage errorMessage = new ErrorMessage("Could not delete the selected Company because it is saved in a Bill.");
			
			model.addAttribute("errorObject", errorMessage);
			
			return "error";
		}
		
		companyRepository.deleteById(Integer.parseInt(companyId));

		return "redirect:/company/getAll";
	}

	@GetMapping("/add")
	public String addCompany(Model model){

		CompanyDto company = new CompanyDto();

		model.addAttribute("company", company);

		return "company/add";
	}

	@PostMapping("/add")
	public String companyAdded(CompanyDto company){

		companyRepository.save(company);

		return "redirect:/company/getAll";
	}

}
