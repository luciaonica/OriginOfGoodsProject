package com.origin.exporter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.origin.entity.Exporter;

public interface ExporterRepository extends JpaRepository<Exporter, Integer>{
	public List<Exporter> findAllByOrderByNameAsc();
	
	@Query("SELECT e FROM Exporter e WHERE e.country.id = ?1 ")
	public List<Exporter> findByCountry(Integer countryId);
}
