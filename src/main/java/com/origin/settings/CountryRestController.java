
package com.origin.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.origin.consignee.ConsigneeRepository;
import com.origin.entity.Consignee;
import com.origin.entity.Country;
import com.origin.entity.Exporter;
import com.origin.exporter.ExporterRepository;

@RestController
public class CountryRestController {
	
	@Autowired private CountryRepository repo;
	
	@Autowired private ExporterRepository exporterRepo;
	
	@Autowired private ConsigneeRepository consigneeRepo;
	
	@GetMapping("/countries/list")
	public List<Country> listAll() {
		return repo.findAllByOrderByNameAsc();
	}
	
	@PostMapping("/countries/save")
	public String save(@RequestBody Country country) {
		Country savedCountry = repo.save(country);
		return String.valueOf(savedCountry.getId());		
	}
	
	@DeleteMapping("/countries/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
	
	@GetMapping("/countries/{id}/exporters")
	public List<ExporterDTO> listExportersByCountry(@PathVariable(name = "id") Integer CountryId) {
		List<ExporterDTO> listExporters = new ArrayList<>();
		
		List<Exporter> exporters = (List<Exporter>) exporterRepo.findByCountry(CountryId);
		for (Exporter exporter : exporters) {
			ExporterDTO dto = new ExporterDTO(exporter.getId(), exporter.getName());
			listExporters.add(dto);
		}
		
		return listExporters;		
	}
	
	@GetMapping("/countries/{id}/consignees")
	public List<ConsigneeDTO> listConsigneesByCountry(@PathVariable(name = "id") Integer CountryId) {
		List<ConsigneeDTO> listConsignees = new ArrayList<>();
		
		List<Consignee> consignees = (List<Consignee>) consigneeRepo.findConsigneesExceptCountry(CountryId);
		for (Consignee consignee : consignees) {
			ConsigneeDTO dto = new ConsigneeDTO(consignee.getId(), consignee.getName());
			listConsignees.add(dto);
		}
		
		return listConsignees;		
	}
}
