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
import org.springframework.web.servlet.view.RedirectView;

import com.example.billTracker.dto.CompanyDto;
import com.example.billTracker.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController{

	@Autowired
	CompanyRepository companyRepository;

	private CompanyDto editCompany;

	@GetMapping("/getAll")
	public String getAll(Model model){

		List<CompanyDto> companies = (List<CompanyDto>) companyRepository.findAll();

		model.addAttribute("companies", companies);

		if(companies.isEmpty())
			return "error";
		return "company/all";
	}

	@PostMapping("/editCompany")
	public String editCompany(@RequestParam("companyId") String companyId, Model model){

		Optional<CompanyDto> company = companyRepository.findById(Integer.parseInt(companyId));

		editCompany = company.get();

		model.addAttribute("company", company);

		if(company.isPresent())
			return "company/edit";
		return "error";
	}

	@PostMapping("/edit")
	public RedirectView editCompany(@ModelAttribute("company") CompanyDto company){

		companyRepository.updateCompany(company.getCompanyName(), editCompany.getCompanyId());

		editCompany = null;

		return new RedirectView("http://localhost:8080/company/getAll");
	}

	@PostMapping("/delete")
	public RedirectView deleteCompany(@RequestParam("companyId") String companyId, Model model){

		companyRepository.deleteById(Integer.parseInt(companyId));

		return new RedirectView("http://localhost:8080/company/getAll");
	}

	@GetMapping("/add")
	public String addCompany(Model model){

		CompanyDto company = new CompanyDto();

		model.addAttribute("company", company);

		return "company/add";
	}

	@PostMapping("/add")
	public RedirectView companyAdded(CompanyDto company){

		companyRepository.save(company);

		return new RedirectView("http://localhost:8080/company/getAll");
	}

	@GetMapping("/findById")
	public String findById(){

		return "";
	}

}
