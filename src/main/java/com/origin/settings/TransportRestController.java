package com.origin.settings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.origin.entity.Transport;

@RestController
public class TransportRestController {
	
	@Autowired private TransportRepository repo;
	
	@GetMapping("/transport/list")
	public List<Transport> listAll() {
		return (List<Transport>) repo.findAll();
	}
	
	@PostMapping("/transport/save")
	public String save(@RequestBody Transport transport) {
		Transport savedTransport = repo.save(transport);
		return String.valueOf(savedTransport.getId());		
	}
	
	@DeleteMapping("/transport/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}

}
