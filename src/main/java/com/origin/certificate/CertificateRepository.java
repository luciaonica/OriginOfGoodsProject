package com.origin.certificate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.origin.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Integer>{
	
	public Long countById(Integer id);	
	
	@Query("SELECT c FROM Certificate c JOIN c.details d"
			+ " WHERE d.goodsDescription LIKE %?1%")
	public Page<Certificate> findAllByGoods(String goodsKeyword, Pageable pageable);
	
	@Query("SELECT c FROM Certificate c WHERE c.exporter.name LIKE %?1%")
	public Page<Certificate> findAllByExporter(String exporterKeyword, Pageable pageable);
	
	@Query("SELECT DISTINCT c FROM Certificate c JOIN c.details d JOIN c.exporter e"
			+ " WHERE d.goodsDescription LIKE %?2% and e.name LIKE %?1%")
	public Page<Certificate> findAll(String exporterKeyword, String goodsKeyword, Pageable pageable);
	
}