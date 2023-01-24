package com.origin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "goods_details")
public class GoodsDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 255, nullable = false, name = "packing_and_marking")
	private String packingAndMarking;
	
	@Column(length = 1024, nullable = false)
	private String goodsDescription;
	
	@Column(length = 256)
	private String originCriteria;
	
	@Column(length = 256)
	private String grossWeightOrOtherUnits;
	
	@ManyToOne
	@JoinColumn(name = "certificate_id")
	private Certificate certificate;

	public GoodsDetail(String packingAndMarking, String goodsDescription, String originCriteria,
			String grossWeightOrOtherUnits, Certificate certificate) {
		this.packingAndMarking = packingAndMarking;
		this.goodsDescription = goodsDescription;
		this.originCriteria = originCriteria;
		this.grossWeightOrOtherUnits = grossWeightOrOtherUnits;
		this.certificate = certificate;
	}
	
	public GoodsDetail() {
		
	}
	
	public GoodsDetail(Integer id, String packingAndMarking, String goodsDescription, String originCriteria,
			String grossWeightOrOtherUnits, Certificate certificate) {
		this.id = id;
		this.packingAndMarking = packingAndMarking;
		this.goodsDescription = goodsDescription;
		this.originCriteria = originCriteria;
		this.grossWeightOrOtherUnits = grossWeightOrOtherUnits;
		this.certificate = certificate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPackingAndMarking() {
		return packingAndMarking;
	}

	public void setPackingAndMarking(String packingAndMarking) {
		this.packingAndMarking = packingAndMarking;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getOriginCriteria() {
		return originCriteria;
	}

	public void setOriginCriteria(String originCriteria) {
		this.originCriteria = originCriteria;
	}

	public String getGrossWeightOrOtherUnits() {
		return grossWeightOrOtherUnits;
	}

	public void setGrossWeightOrOtherUnits(String grossWeightOrOtherUnits) {
		this.grossWeightOrOtherUnits = grossWeightOrOtherUnits;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}	
}
