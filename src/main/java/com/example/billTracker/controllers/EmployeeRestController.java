package com.example.billTracker.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.billTracker.jasperService.JasperEmployeeService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/jasperEmployee")
public class EmployeeRestController{

	@Autowired
	JasperEmployeeService jasperEmployeeService;
	
	@RequestMapping("/generatePdf")
	public ResponseEntity<byte[]> getAllEmployeesJasper() throws JRException, IOException {
		
		jasperEmployeeService.exportReport("pdf");
	
		byte[] pdfContent = jasperEmployeeService.getPdfFileFromProject();
		
		HttpHeaders headers = new HttpHeaders();
		
	    headers.setContentType(MediaType.APPLICATION_PDF);
	    
	    String filename = "employees.pdf";
	    
	    headers.setContentDispositionFormData(filename, filename);
	    
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    
	    ResponseEntity<byte[]> response = new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
	    
	    return response;
	
	}
	
}
