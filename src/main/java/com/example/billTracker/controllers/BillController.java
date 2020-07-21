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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.billTracker.dto.BillDto;
import com.example.billTracker.dto.CompanyDto;
import com.example.billTracker.repositories.BillRepository;
import com.example.billTracker.repositories.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/bill")
public class BillController{

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	BillDto editBill;
	
	@GetMapping("/getAll")
	public String getAll(Model model){

		List<BillDto> bills = (List<BillDto>) billRepository.findAll();

		model.addAttribute("bills", bills);

		if(bills.isEmpty())
			return "error";
		return "bill/all";
	}
	
	@GetMapping("/add")
	public String addBill(Model model){

		BillDto bill = new BillDto();

		model.addAttribute("bill", bill);
		
		model.addAttribute("companies", companyRepository.findAll());

		return "bill/add";
	}

	@PostMapping("/add")
	public RedirectView billAdded(BillDto bill, RedirectAttributes redirectAttrs){
		
		if(bill.getReceivingCompany().isParentCompany() ^ bill.getPayingCompany().isParentCompany()) {
			
			billRepository.save(bill);

			return new RedirectView("http://localhost:8080/bill/getAll");
		} else {	
			
			RedirectView rv = new RedirectView("http://localhost:8080/error");
			
			//redirectAttrs.addAttribute("errorMessage", "Neither of the selected companies is a Parent company");
			
			return rv;
		}
		
	}
	
	@PostMapping("/delete")
	public RedirectView deleteBill(@RequestParam("billId") String billId, Model model) {

	    billRepository.deleteById(Integer.parseInt(billId));
      
	    return new RedirectView("http://localhost:8080/bill/getAll");
	}
	
	
	@PostMapping("/editBill")
	public String editBill(@RequestParam("billId") String billId, Model model) {

	    Optional<BillDto> bill = billRepository.findById(Integer.parseInt(billId));
	    
	    editBill = bill.get();

	    model.addAttribute("bill", bill.get());
	    
	    model.addAttribute("companies", companyRepository.findAll());
      
	    if(bill.isPresent()) return "bill/edit";
	    return "error";
	}
	
	@GetMapping("/find")
	public String find(Model model) {
		
		BillDto bill = new BillDto();

		model.addAttribute("bill", bill);
		
		return "bill/find";
	}
	
	@PostMapping("/find")
	public String findById(BillDto bill, Model model) {
				
		Optional<BillDto> foundBill = billRepository.findById(bill.getBillId());
		
		if(!foundBill.isPresent())	return "bill/find";
				
		model.addAttribute("bill", foundBill.get());
		
		editBill = foundBill.get();
		
		model.addAttribute("companies", companyRepository.findAll());
		
		return "bill/edit";

	}
	
	@PostMapping("/edit")
	public RedirectView editBill(@ModelAttribute("bill") BillDto bill) {
						
		bill.setBillId(editBill.getBillId());
		
		billRepository.save(bill);
		
		editBill = null;
		
		return new RedirectView("http://localhost:8080/bill/getAll");
	}
	
	@GetMapping("/findAmounts")
	public String findByAmounts(Model model) {
		
		Double amountFrom = 0.0;
		
		model.addAttribute("amountFrom", amountFrom);
		
		Double amountTo = 0.0;
		
		model.addAttribute("amountTo", amountTo);
		
		return "bill/findBetweenAmounts";
	}
	
	@PostMapping("/findBetweenAmounts")
	public String findBetweenAmounts(@RequestParam("amountFrom") String amountFrom, @RequestParam("amountTo") String amountTo, Model model) {
				
		if(Double.parseDouble(amountFrom) > Double.parseDouble(amountTo)) {
			return "error";
		}
		
		List<BillDto> bills = billRepository.findBillsByAmountValueBetween(Double.parseDouble(amountFrom), Double.parseDouble(amountTo));
		
		if(bills.isEmpty()) {
			return "error";
		}
		
		model.addAttribute("bills", bills);
		
		return "bill/all";
	}
	
	@GetMapping("/findByCompanies")
	public String findByCompaniesGet(Model model) {
		
		List<CompanyDto> companies = (List<CompanyDto>) companyRepository.findAll();
		
		model.addAttribute("companies", companies);
		
		String payingCompanyId = "payingCompanyId";
		
		model.addAttribute("payingCompanyId", payingCompanyId);
		
		String receivingCompanyId = "receivingCompanyId";
		
		model.addAttribute("receivingCompanyId", receivingCompanyId);
		
		return "bill/findByCompanies";
	}
	
	@PostMapping("/findByCompanies")
	public String findByCompanies(@RequestParam("payingCompanyId") int payingCompanyId, 
			@RequestParam("receivingCompanyId") int receivingCompanyId, Model model) {
				
		List<BillDto> bills = 
				billRepository.findBillsByPayingCompanyCompanyIdIsAndReceivingCompanyCompanyIdIs(payingCompanyId, receivingCompanyId);
		
		if(bills.isEmpty()) {
			return "error";
		}
		
		model.addAttribute("bills", bills);
		
		return "bill/all";
	}
	
	
	
	
	
	
	

}
