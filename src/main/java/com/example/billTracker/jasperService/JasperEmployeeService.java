package com.example.billTracker.jasperService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.billTracker.dto.EmployeeDto;
import com.example.billTracker.dto.SalaryDto;
import com.example.billTracker.repositories.EmployeeRepository;
import com.example.billTracker.repositories.SalaryRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SalaryRepository salaryRepository;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException{

		String absolutePathToResources = getResourcesAbsolutePath();

		AggregatedPageImpl<EmployeeDto> employees = (AggregatedPageImpl<EmployeeDto>) employeeRepository.findAll();

		File file = ResourceUtils.getFile("classpath:Employees.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		List<EmployeeDto> employeesList = (List<EmployeeDto>) employees.getContent();

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeesList);

		Map<String, Object> parameters = new HashedMap();

		parameters.put("Created by:", "Luka Orlovic");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		if(reportFormat.equalsIgnoreCase("html")){
			JasperExportManager.exportReportToHtmlFile(jasperPrint, absolutePathToResources + "\\employees.html");
		} else if(reportFormat.equalsIgnoreCase("pdf")){
			JasperExportManager.exportReportToPdfFile(jasperPrint, absolutePathToResources + "\\employees.pdf");
		}

		return "employee/employees";
	}

	public String getResourcesAbsolutePath(){

		File file = new File("src/main/resources/templates/employee");

		return file.getAbsolutePath();
	}

	public byte[] getPdfFileFromProject() throws IOException{

		Path pdfPath = Paths.get(getResourcesAbsolutePath() + "\\employees.pdf");

		byte[] pdf = Files.readAllBytes(pdfPath);

		return pdf;
	}

}
