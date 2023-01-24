package com.origin.certificate;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.origin.entity.Certificate;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CertificateService {
	public static final int CERTIFICATES_PER_PAGE = 4;
	
	@Autowired private CertificateRepository repo;
	
	public List<Certificate> listAll(){
		return (List<Certificate>) repo.findAll();		
	}
	
	public Page<Certificate> listByPage(int pageNum, String sortField, String sortDir, String exporterKeyword, String goodsKeyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, CERTIFICATES_PER_PAGE, sort);
		
		if (exporterKeyword != null) {
			if (goodsKeyword != null) {
				return repo.findAll(exporterKeyword, goodsKeyword, pageable);	
			} else return repo.findAllByExporter(exporterKeyword, pageable);							
		} else if (goodsKeyword != null) {
			return repo.findAllByGoods(goodsKeyword, pageable);
		}
				
		return repo.findAll(pageable);
	}
	
	public Certificate save(Certificate certificate) {
		if (certificate.getId() == null) {
			certificate.setCertificateDate(new Date());			
		} 
				
		return repo.save(certificate);
	}
	
	public void delete(Integer id) throws CertificateNotFoundException{
		Long countById = repo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new CertificateNotFoundException("Could not found any certificate with ID " + id);
		}
		
		repo.deleteById(id);
	}
	
	public Certificate get(Integer id) throws CertificateNotFoundException {		
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new CertificateNotFoundException("Could not find any certificate with ID " + id);
		}
	}
	
}
