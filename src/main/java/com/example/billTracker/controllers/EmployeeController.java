package com.example.billTracker.controllers;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.billTracker.dto.EmployeeDto;
import com.example.billTracker.dto.RoleDto;
import com.example.billTracker.dto.SalaryDto;
import com.example.billTracker.helper.ErrorMessage;
import com.example.billTracker.jasperService.JasperEmployeeService;
import com.example.billTracker.repositories.EmployeeRepository;
import com.example.billTracker.repositories.SalaryRepository;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/employee")
public class EmployeeController{

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SalaryRepository salaryRepository;
	
	@Autowired
	JasperEmployeeService jasperEmployeeService;
	
	EmployeeDto editEmployee;
	
	@GetMapping("/getAll")
	public String getAll(Model model){

		PageImpl<EmployeeDto> employees = (PageImpl<EmployeeDto>) employeeRepository.findAll();

		model.addAttribute("employees", employees);

		if(!employees.isEmpty()) return "employee/getAll";
			
		ErrorMessage errorMessage = new ErrorMessage("Could not find any employees.");
		
		model.addAttribute("errorObject", errorMessage);
		
		return "error";
	}

	@GetMapping("/add")
	public String addEmployee(Model model){
		
		EmployeeDto employee = new EmployeeDto();

		model.addAttribute("employee", employee);
		
		model.addAttribute("roles", RoleDto.values());

		return "employee/addEmployee";
	}

	@PostMapping("/add")
	public String employeeAdded(@ModelAttribute("employee") EmployeeDto employee){
		 
		int id = 1;
		
		EmployeeDto e = null;
		
		try{
			e = employeeRepository.findTopByOrderByEmployeeIdDesc();
		} catch (Exception e1){
		}
		
		if(e != null) {
			id = e.getEmployeeId() + 1;
		}
		
		employee.setEmployeeId(id);
		
		employeeRepository.save(employee);

		return "redirect:/employee/getAll";
	}

	List<EmployeeDto> getAllWithoutSalary(){

		List<EmployeeDto> employeesWithoutSalaries = new LinkedList<>();

		PageImpl<EmployeeDto> employees = (PageImpl<EmployeeDto>) employeeRepository.findAll();

		PageImpl<SalaryDto> salaries = (PageImpl<SalaryDto>) salaryRepository.findAll();

		for(EmployeeDto e : employees){
			if(salaries.isEmpty()) employeesWithoutSalaries.add(e);
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
	public String deleteEmployee(@RequestParam("employeeId") String employeeId, Model model) {

		SalaryDto salary = salaryRepository.findByEmployeeEmployeeId(Integer.parseInt(employeeId));
		
		if(salary == null) {			
			employeeRepository.deleteById(Integer.parseInt(employeeId));

			return "redirect:/employee/getAll";
		}
		
		
		ErrorMessage errorMessage = new ErrorMessage("The selected Employee could not be deleted because it is contained in a Salary.");
		
		model.addAttribute("errorObject", errorMessage);
		
	    return "error";
      
	}
	
	@PostMapping("/editEmployee")
	public String editEmployee(@RequestParam("employeeId") String employeeId, Model model) {

	    Optional<EmployeeDto> employee = employeeRepository.findById(Integer.parseInt(employeeId));
	    
	    editEmployee = employee.get();

	    model.addAttribute("employee", employee.get());
	        
	    if(employee.isPresent()) return "employee/edit";
	    
	    ErrorMessage errorMessage = new ErrorMessage("The selected employee could not be found.");
		
		model.addAttribute("errorObject", errorMessage);
		
	    return "error";
	}
	
	@PostMapping("/edit")
	public String editEmployee(@ModelAttribute("employee") EmployeeDto employee) {
						
		employee.setEmployeeId(editEmployee.getEmployeeId());
		
		employeeRepository.save(employee);
		
		editEmployee = null;
		
		return "redirect:/employee/getAll";
	}
	
	@GetMapping("/getAllJasper")
	public String getAllEmployees() throws FileNotFoundException, JRException {
		
		return jasperEmployeeService.exportReport("html");
		
	}
	
}
