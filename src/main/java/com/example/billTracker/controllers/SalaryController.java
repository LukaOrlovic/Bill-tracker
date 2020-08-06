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

import com.example.billTracker.dto.SalaryDto;
import com.example.billTracker.helper.ErrorMessage;
import com.example.billTracker.repositories.EmployeeRepository;
import com.example.billTracker.repositories.SalaryRepository;

@Controller
@RequestMapping("/salary")
public class SalaryController{

	@Autowired
	SalaryRepository salaryRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeController employeeControler;
	
	SalaryDto editSalary = null;
	
	@GetMapping("/getAll")
	public String getAll(Model model) {
		
		List<SalaryDto> salaries = (List<SalaryDto>) salaryRepository.findByOrderBySalaryIdAsc();
				
		if(salaries.isEmpty()) return "error";
		
		model.addAttribute("salaries", salaries);
		
		return "salary/getAll";
	}
	
	@PostMapping("/editSalary")
	public String editSalary(@RequestParam("salaryId") String salaryId, Model model) {

	    Optional<SalaryDto> salary = salaryRepository.findById(Integer.parseInt(salaryId));
	    
	    editSalary = salary.get();

	    model.addAttribute("salary", salary);
      
	    if(salary.isPresent()) return "salary/edit";
	    
	    return "error";
	}
	
	@PostMapping("/edit")
	public String editSalary(@ModelAttribute("salary") SalaryDto salary) {
				
		salary.setSalaryId(editSalary.getSalaryId());
		
		salaryRepository.save(salary);
		
		editSalary = null;
		
		return "redirect:/salary/getAll";
	}
	
	@PostMapping("/delete")
	public String deleteSalary(@RequestParam("salaryId") String salaryId, Model model) {

	    salaryRepository.deleteById(Integer.parseInt(salaryId));
	          
	    return "redirect:/salary/getAll";
	}
	
	@GetMapping("/add")
	public String addCountry(Model model) {
		
		SalaryDto salary= new SalaryDto();
		
		model.addAttribute("salary", salary);
								
		model.addAttribute("employees", employeeControler.getAllWithoutSalary());
						
		return "salary/add";
	}
	
	@PostMapping("/add")
	public String salaryAdded(SalaryDto salary) {
		
		salaryRepository.save(salary);
		
		return "redirect:/salary/getAll";
	}
	
	@GetMapping("/findAmounts")
	public String findByAmounts(Model model) {
		
		Double amountFrom = 0.0;
		
		model.addAttribute("amountFrom", amountFrom);
		
		Double amountTo = 0.0;
		
		model.addAttribute("amountTo", amountTo);
		
		return "salary/findBetweenAmounts";
	}
	
	@PostMapping("/findBetweenAmounts")
	public String findBetweenAmounts(@RequestParam("amountFrom") String amountFrom, @RequestParam("amountTo") String amountTo, Model model) {
				
		if(Double.parseDouble(amountFrom) > Double.parseDouble(amountTo)) {
			ErrorMessage errorMessage = new ErrorMessage("Amount To must be larger than amount From.");
			
			model.addAttribute("errorObject", errorMessage);
			
			return "error";
		}
		
		List<SalaryDto> salaries = salaryRepository.findSalariesByAmountValueBetween(Double.parseDouble(amountFrom), Double.parseDouble(amountTo));
		
		if(salaries.isEmpty()) {
			ErrorMessage errorMessage = new ErrorMessage("Could not find Salary between inserted amounts.");
			
			model.addAttribute("errorObject", errorMessage);
			
			return "error";
		}
		
		model.addAttribute("salaries", salaries);
		
		return "salary/getAll";
	}
	
}
