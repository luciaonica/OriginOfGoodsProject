package com.origin.exporter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.origin.entity.Country;
import com.origin.entity.Exporter;
import com.origin.settings.CountryRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExporterController {		
		@Autowired
		private ExporterRepository repo;
		
		@Autowired
		private CountryRepository countryRepo;
		
		@GetMapping("/exporters")
		public String listExporters(Model model) {
			List<Exporter> listExporters = (List<Exporter>) repo.findAll();
			model.addAttribute("listExporters", listExporters);
			
			return "exporters/exporters";		
		}
		
		@GetMapping("/exporters/new")
		public String showNewExporterForm(Model model) {			
			List<Country> listCountries =  (List<Country>) countryRepo.findAll();					
		
			model.addAttribute("listCountries", listCountries);
			model.addAttribute("exporter", new Exporter());
			
			return "exporters/exporter_form";		
		}
		
		@PostMapping("/exporters/save")
		public String saveExporter(Exporter exporter) {
			repo.save(exporter);
			
			return "redirect:/exporters";			
		}
		
		@GetMapping("/exporters/edit/{id}")
		public String showEditExporterForm(@PathVariable("id") Integer id, Model model) {
			Exporter exporter = repo.findById(id).get();
			List<Country> listCountries =  (List<Country>) countryRepo.findAll();					
			
			model.addAttribute("listCountries", listCountries);
			model.addAttribute("exporter", exporter);
			
			return "exporters/exporter_form";		
		}
		
		@GetMapping("/exporters/delete/{id}")
		public String deleteExporter(@PathVariable("id") Integer id, Model model) {
			repo.deleteById(id);
			
			return "redirect:/exporters";		
		}

		@GetMapping("/exporters/export/csv")
		public void exportToCSV(HttpServletResponse response) throws IOException {	
			List<Exporter> listExporters = repo.findAllByOrderByNameAsc();
			ExporterCsvExporter exporter = new ExporterCsvExporter();
			exporter.export(listExporters, response);
		}
}
