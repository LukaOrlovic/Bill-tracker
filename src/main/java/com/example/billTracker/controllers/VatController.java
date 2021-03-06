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
import com.example.billTracker.dto.CountryDto;
import com.example.billTracker.dto.VatDto;
import com.example.billTracker.helper.ErrorMessage;
import com.example.billTracker.repositories.CompanyRepository;
import com.example.billTracker.repositories.CountryRepository;
import com.example.billTracker.repositories.VatRepository;

@Controller
@RequestMapping("/vat")
public class VatController{
	
	@Autowired
	VatRepository vatRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	VatDto editVat;

	@GetMapping("/all")
	public String getAll(Model model) {
		
		List<VatDto> vats = (List<VatDto>) vatRepository.findAll();
		
		if(vats.isEmpty()) {
			return "error";
		}
		
		model.addAttribute("vats", vats);
		
		return "vat/all";
	}
	
	@GetMapping("/add")
	public String addVat(Model model) {
		
		List<CountryDto> countries = (List<CountryDto>) countryRepository.findAll();
		model.addAttribute("countries", countries);
		
		CompanyDto company = companyRepository.findCompanyByParentCompanyIsTrue();
		model.addAttribute("company", company);
		
		VatDto vat = new VatDto();
		
		vat.setCompany(company);
		
		model.addAttribute("vat", vat);
		
		return "vat/add";
	}
	
	@PostMapping("/add")
	public String vatAdded(VatDto vat) {
		CompanyDto company = companyRepository.findCompanyByParentCompanyIsTrue();
		
		vat.setCompany(company);
		
		vatRepository.save(vat);
		
		return "redirect:/vat/all";
	}
	
	@PostMapping("/editVat")
	public String editVat(@RequestParam("vatId") String vatId, Model model) {

	    Optional<VatDto> vat = vatRepository.findById(Integer.parseInt(vatId));
	    
	    editVat = vat.get();

	    model.addAttribute("vat", vat);
      
	    if(vat.isPresent()) return "vat/edit";
	    
	    ErrorMessage errorMessage = new ErrorMessage("Could not find the selected VAT.");
		
		model.addAttribute("errorObject", errorMessage);
		
		return "error";
	}
	
	@PostMapping("/edit")
	public String editVat(@ModelAttribute("vat") VatDto vat) {
			
		vat.setVatId(editVat.getVatId());
		
		vatRepository.save(vat);
		
		editVat = null;
		
		return "redirect:/vat/getAll";
	}
	
	@PostMapping("/delete")
	public String deleteVat(@RequestParam("vatId") String vatId, Model model) {

	    vatRepository.deleteById(Integer.parseInt(vatId));
      
	    return "redirect:/vat/all";
	}
}
