package com.example.billTracker.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.billTracker.config.EmployeeListBean;
import com.example.billTracker.dto.BillDto;
import com.example.billTracker.dto.CountryDto;
import com.example.billTracker.dto.EmployeeDto;
import com.example.billTracker.dto.SalaryDto;
import com.example.billTracker.repositories.EmployeeRepository;
import com.example.billTracker.repositories.SalaryRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController{

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SalaryRepository salaryRepository;
	
	EmployeeDto editEmployee;
	
	@GetMapping("/bla")
	public String bla() {
		System.out.println(employeeRepository.findTopByOrderByEmployeeIdDesc().getEmployeeId());
		
		return "home";
	}
	
	@GetMapping("/getAll")
	public String getAll(Model model){

		AggregatedPageImpl<EmployeeDto> employees = (AggregatedPageImpl<EmployeeDto>) employeeRepository.findAll();

		model.addAttribute("employees", employees);

		if(employees.isEmpty())
			return "error";
		return "employee/getAll";
	}

	@GetMapping("/add")
	public String addEmployee(Model model){
		
		EmployeeDto employee = new EmployeeDto();

		model.addAttribute("employee", employee);

		return "employee/addEmployee";
	}

	@PostMapping("/add")
	public RedirectView countryAdded(EmployeeDto employee){
		 
		EmployeeDto e = employeeRepository.findTopByOrderByEmployeeIdDesc();
		int id = e.getEmployeeId() + 1;

		employee.setEmployeeId(id);
		employeeRepository.save(employee);

		return new RedirectView("http://localhost:8080/employee/getAll");
	}

	List<EmployeeDto> getAllWithoutSalary(){

		List<EmployeeDto> employeesWithoutSalaries = new LinkedList<>();

		AggregatedPageImpl<EmployeeDto> employees = (AggregatedPageImpl<EmployeeDto>) employeeRepository.findAll();

		AggregatedPageImpl<SalaryDto> salaries = (AggregatedPageImpl<SalaryDto>) salaryRepository.findAll();

		for(EmployeeDto e : employees){
			for(SalaryDto s : salaries){
				if(s.getEmployee().getEmployeeId() == e.getEmployeeId()){
					break;
				}
				if(salaries.getContent().get(salaries.getContent().size() - 1) .equals(s)){
					employeesWithoutSalaries.add(e);
				}
			}
		}

		return employeesWithoutSalaries;

	}
	
	@PostMapping("/delete")
	public RedirectView deleteEmployee(@RequestParam("employeeId") String employeeId, Model model) {

	    employeeRepository.deleteById(Integer.parseInt(employeeId));
      
	    return new RedirectView("http://localhost:8080/employee/getAll");
	}
	
	@PostMapping("/editEmployee")
	public String editEmployee(@RequestParam("employeeId") String employeeId, Model model) {

	    Optional<EmployeeDto> employee = employeeRepository.findById(Integer.parseInt(employeeId));
	    
	    editEmployee = employee.get();

	    model.addAttribute("employee", employee.get());
	        
	    if(employee.isPresent()) return "employee/edit";
	    return "error";
	}
	
	@PostMapping("/edit")
	public RedirectView editEmployee(@ModelAttribute("employee") EmployeeDto employee) {
						
		employee.setEmployeeId(editEmployee.getEmployeeId());
		
		employeeRepository.save(employee);
		
		editEmployee = null;
		
		return new RedirectView("http://localhost:8080/employee/getAll");
	}
}
