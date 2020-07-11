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

import com.example.billTracker.dto.CountryDto;
import com.example.billTracker.repositories.CountryRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/country")
public class CountryController{
	
	@Autowired
	private CountryRepository countryRepository;
	
	private CountryDto editCountry;
	
	@GetMapping("/getAll")
	public String getAll(Model model){
		
		List<CountryDto> countries = (List<CountryDto>) countryRepository.findAll();
		
		model.addAttribute("countries", countries);
				
		if(countries.isEmpty()) return "error";
		return "country/all";
	}

	@GetMapping("/add")
	public String addCountry(Model model) {
		CountryDto country = new CountryDto();
		
		model.addAttribute("country", country);
		
		return "country/addCountry";
	}
	
	@PostMapping("/add")
	public RedirectView countryAdded(CountryDto country) {
		
		countryRepository.save(country);
		
		return new RedirectView("http://localhost:8080/country/getAll");
	}
	
	@PostMapping("/editCountry")
	public String editCountry(@RequestParam("countryId") String countryId, Model model) {

	    Optional<CountryDto> country = countryRepository.findById(Integer.parseInt(countryId));
	    
	    editCountry = country.get();

	    model.addAttribute("country", country);
      
	    if(country.isPresent()) return "country/edit";
	    return "error";
	}
	
	@PostMapping("/edit")
	public RedirectView editCountry(@ModelAttribute("country") CountryDto country) {
				
		countryRepository.updateCountry(country.getName(), editCountry.getCountryId());
		
		editCountry = null;
		
		return new RedirectView("http://localhost:8080/country/getAll");
	}
	
	@PostMapping("/delete")
	public RedirectView deleteCountry(@RequestParam("countryId") String countryId, Model model) {

	    countryRepository.deleteById(Integer.parseInt(countryId));
      
	    return new RedirectView("http://localhost:8080/country/getAll");
	}
}
