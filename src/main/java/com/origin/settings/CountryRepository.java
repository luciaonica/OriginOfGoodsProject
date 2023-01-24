package com.origin.settings;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.origin.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer>{
	public List<Country> findAllByOrderByNameAsc();
}
