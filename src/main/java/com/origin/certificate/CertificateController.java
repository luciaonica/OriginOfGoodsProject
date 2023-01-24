package com.origin.certificate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.origin.consignee.ConsigneeRepository;
import com.origin.entity.Certificate;
import com.origin.entity.Consignee;
import com.origin.entity.Country;
import com.origin.entity.Exporter;
import com.origin.entity.Transport;
import com.origin.exporter.ExporterRepository;
import com.origin.settings.CountryRepository;
import com.origin.settings.TransportRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CertificateController {
	@Autowired
	private ExporterRepository exporterRepo;
	
	@Autowired
	private ConsigneeRepository consigneeRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired private CertificateService service;
	
	@Autowired
	private TransportRepository transportRepo;
	
	@GetMapping("/certificates")
	public String listFirstPage(Model model) {		
		return listByPage(1, model, "id", "asc", null, null);		
	}
	
	@GetMapping("/certificates/page/{pageNum}")
	public String listByPage(
			@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir,
			@Param("keyword") String exporterKeyword, @Param("keyword") String goodsKeyword
			) {	
				
		Page<Certificate> page = service.listByPage(pageNum, sortField, sortDir, exporterKeyword, goodsKeyword);
		
		List<Certificate> listCertificates = page.getContent();
		
		long startCount = (pageNum - 1) * CertificateService.CERTIFICATES_PER_PAGE + 1;
		long endCount = startCount + CertificateService.CERTIFICATES_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
				
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems",  page.getTotalElements());
		model.addAttribute("listCertificates", listCertificates);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("goodsKeyword", goodsKeyword);	
		model.addAttribute("exporterKeyword", exporterKeyword);
		
		return "certificates/certificates";		
	}
	
	@GetMapping("/certificates/new")
	public String showNewCertificateForm(Model model) {			
		List<Exporter> listExporters = (List<Exporter>) exporterRepo.findAll();
		List<Consignee> listConsignees = (List<Consignee>) consigneeRepo.findAll();
		List<Country> listCountries = (List<Country>) countryRepo.findAll();	
		List<Transport> listTransports = (List<Transport>) transportRepo.findAll();	
		Certificate certificate = new Certificate();
		
		model.addAttribute("listExporters", listExporters);
		model.addAttribute("listConsignees", listConsignees);
		model.addAttribute("listCountries", listCountries);
		model.addAttribute("certificate", certificate);
		model.addAttribute("listTransports", listTransports);
		model.addAttribute("pageTitle", "Create New Certificate");
		
		return "certificates/certificate_form";		
	}
	
	@PostMapping("/certificates/save")
	public String saveCertificate(Certificate certificate, RedirectAttributes ra,
			@RequestParam(name = "goodMarkings", required = false) String[] goodMarkings,
			@RequestParam(name = "goodDescriptions", required = false) String[] goodDescriptions,
			@RequestParam(name = "goodCriterias", required = false) String[] goodCriterias,
			@RequestParam(name = "goodQuantities", required = false) String[] goodQuantities) {
		
		setGoodsDetails(goodMarkings, goodDescriptions, goodCriterias, goodQuantities, certificate);
		 
		
		certificate.setCertificateDate(new Date());
		
		
		service.save(certificate);
				
		ra.addFlashAttribute("message", "The certificate has been saved successfully.");
		
		return "redirect:/certificates";			
	}

	private void setGoodsDetails(String[] goodMarkings, String[] goodDescriptions, String[] goodCriterias,
			String[] goodQuantities, Certificate certificate) {
		if (goodDescriptions == null || goodDescriptions.length == 0) return;
		
		for (int count = 0; count < goodDescriptions.length; count++) {
			String marking = goodMarkings[count];
			String description = goodDescriptions[count];
			String criteria = goodCriterias[count];
			String quantity = goodQuantities[count];
			
			if (!marking.isEmpty() && !description.isEmpty() && !criteria.isEmpty() && !quantity.isEmpty()) {
				certificate.addGood(marking, description, criteria, quantity);
			}
		}
		
	}
	
	
	@GetMapping("/certificates/edit/{id}")
	public String editCertificate(@PathVariable("id") Integer id, Model model, 
			RedirectAttributes ra) {
		
		try {
			Certificate certificate = service.get(id);
			List<Exporter> listExporters = (List<Exporter>) exporterRepo.findAll();
			List<Consignee> listConsignees = (List<Consignee>) consigneeRepo.findAll();
			List<Country> listCountries = (List<Country>) countryRepo.findAll();	
			List<Transport> listTransports = (List<Transport>) transportRepo.findAll();	
			
			model.addAttribute("listExporters", listExporters);
			model.addAttribute("listConsignees", listConsignees);
			model.addAttribute("listCountries", listCountries);
			model.addAttribute("certificate", certificate);
			model.addAttribute("listTransports", listTransports);
			model.addAttribute("pageTitle", "Update Certificate");
			
			return "certificates/certificate_form";	
		} catch (CertificateNotFoundException ex) {			
			ra.addFlashAttribute("message", ex.getMessage());	
			
			return "redirect:/certificates";			
		}
	}	
	
	@GetMapping("/certificates/delete/{id}")
	public String deleteCertificate(@PathVariable("id") Integer id, Model model, 
			RedirectAttributes ra) {
		try {
			service.delete(id);		
			
			ra.addFlashAttribute("message", 
					"The certificate with ID " + id + " has been deleted successfully");			
		}catch (CertificateNotFoundException ex) {			
			ra.addFlashAttribute("message", ex.getMessage());				
		}
		
		return "redirect:/certificates";		
	}	
	
	@GetMapping("/certificates/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {	
		List<Certificate> listCertificates = service.listAll();
		CertificateCsvExporter exporter = new CertificateCsvExporter();
		exporter.export(listCertificates, response);
	}
		
	@GetMapping("/certificates/detail/{id}")
	public String viewCertificateDetails(@PathVariable(name = "id") Integer id, Model model,
			RedirectAttributes ra) {		 
		try {
			Certificate certificate = service.get(id);									
			model.addAttribute("certificate", certificate);			
			
			return "certificates/certificate_detail_modal";
			
		} catch (CertificateNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());		
			
			return "redirect:/certificates";
		}
	}
}
