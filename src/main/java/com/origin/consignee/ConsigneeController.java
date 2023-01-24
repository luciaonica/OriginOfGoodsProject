package com.origin.consignee;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.origin.entity.Consignee;
import com.origin.entity.Country;
import com.origin.settings.CountryRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ConsigneeController {
	@Autowired
	private  ConsigneeRepository repo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@GetMapping("/consignees")
	public String listConsignees(Model model) {
		List< Consignee> listConsignees = (List<Consignee>) repo.findAll();
		model.addAttribute("listConsignees", listConsignees);
		
		return "consignees/consignees";		
	}
	
	@GetMapping("/consignees/new")
	public String showNewConsigneeForm(Model model) {			
		List<Country> listCountries =  (List<Country>) countryRepo.findAll();					
	
		model.addAttribute("listCountries", listCountries);
		model.addAttribute("consignee", new Consignee());
		
		return "consignees/consignee_form";		
	}
	
	@PostMapping("/consignees/save")
	public String saveConsignee(Consignee consignee) {
		repo.save(consignee);
		
		return "redirect:/consignees";			
	}
	
	@GetMapping("/consignees/edit/{id}")
	public String showEditConsigneeForm(@PathVariable("id") Integer id, Model model) {
		Consignee consignee = repo.findById(id).get();
		List<Country> listCountries =  (List<Country>) countryRepo.findAll();					
		
		model.addAttribute("listCountries", listCountries);
		model.addAttribute("consignee", consignee);
		
		return "consignees/consignee_form";		
	}
	
	@GetMapping("/consignees/delete/{id}")
	public String deleteConsignee(@PathVariable("id") Integer id, Model model) {
		repo.deleteById(id);
		
		return "redirect:/consignees";		
	}

	@GetMapping("/consignees/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {	
		List<Consignee> listConsignees = repo.findAllByOrderByNameAsc();
		ConsigneeCsvExporter exporter = new ConsigneeCsvExporter();
		exporter.export(listConsignees, response);
	}
}
