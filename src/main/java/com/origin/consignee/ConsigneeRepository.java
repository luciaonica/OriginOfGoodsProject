package com.origin.consignee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.origin.entity.Consignee;

public interface ConsigneeRepository extends JpaRepository<Consignee, Integer>{	
	
	public List<Consignee> findAllByOrderByNameAsc();
	
	//showing the consignees from other countries
	@Query("SELECT c FROM Consignee c WHERE c.country.id != ?1 ")
	public List<Consignee> findConsigneesExceptCountry(Integer countryId);

}
