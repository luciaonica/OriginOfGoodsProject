package com.origin.settings;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.origin.entity.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Integer>{
	
	public List<Currency> findAllByOrderByNameAsc();
}
