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
	
	@PostMapping("/edit")
	public RedirectView editBill(@ModelAttribute("bill") BillDto bill) {
				
		bill.setBillId(editBill.getBillId());
		
		billRepository.save(bill);
		
		editBill = null;
		
		return new RedirectView("http://localhost:8080/bill/getAll");
	}

}
