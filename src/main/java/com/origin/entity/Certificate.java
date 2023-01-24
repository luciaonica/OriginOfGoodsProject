package com.origin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "certificates")
public class Certificate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 16, nullable = false, unique = true, name = "serial_number")
	private String serialNumber;
	
	@Column(name = "certificate_date", updatable = false)
	private Date certificateDate;

	@ManyToOne
	@JoinColumn(name = "exporter_id")
	private Exporter exporter;
	
	@ManyToOne
	@JoinColumn(name = "consignee_id")
	private Consignee consignee;
	
	@ManyToOne
	@JoinColumn(name = "transport_id")
	private Transport transport;
	
	@Column(length = 256)
	private String remarks;
	
	@OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GoodsDetail> details = new ArrayList<>();
	
	@Column(length = 256)
	private String invoiceData;
	
	public Certificate() {
		
	}

	public Certificate(String serialNumber, Date certificateDate, Exporter exporter, Consignee consignee,
			Transport transport, String remarks, List<GoodsDetail> details, String invoiceData) {
		
		this.serialNumber = serialNumber;
		this.certificateDate = certificateDate;
		this.exporter = exporter;
		this.consignee = consignee;		
		this.transport = transport;
		this.remarks = remarks;
		this.details = details;
		this.invoiceData = invoiceData;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}

	public Exporter getExporter() {
		return exporter;
	}

	public void setExporter(Exporter exporter) {
		this.exporter = exporter;
	}

	public Consignee getConsignee() {
		return consignee;
	}

	public void setConsignee(Consignee consignee) {
		this.consignee = consignee;
	}
	
	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<GoodsDetail> getDetails() {
		return details;
	}

	public void setDetails(List<GoodsDetail> details) {
		this.details = details;
	}

	public String getInvoiceData() {
		return invoiceData;
	}

	public void setInvoiceData(String invoiceData) {
		this.invoiceData = invoiceData;
	}
	
	public void addGood(String packingAndMarking, String goodsDescription, String originCriteria,
			String grossWeightOrOtherUnits) {
		this.details.add(new GoodsDetail(packingAndMarking, goodsDescription, originCriteria,
			grossWeightOrOtherUnits, this));
	}
	
	public void addGood(Integer id, String packingAndMarking, String goodsDescription, String originCriteria,
			String grossWeightOrOtherUnits) {
		this.details.add(new GoodsDetail(id, packingAndMarking, goodsDescription, originCriteria,
			grossWeightOrOtherUnits, this));
	}	

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", serialNumber=" + serialNumber + ", certificateDate=" + certificateDate
				+ ", exporter=" + exporter.getName() + ", consignee=" + consignee.getName() + ", country=" + exporter.getCountryName() + ", transport="
				+ transport.getType() + ", remarks=" + remarks + ", invoiceData=" + invoiceData + "]";
	}
	
	@Transient
	public String getExporterName() {
		
		return this.exporter.getName();
	}
	
	@Transient
	public String getConsigneeName() {
		
		return this.consignee.getName();
	}
	
	@Transient
	public String getDestinationCountryName() {
		
		return this.consignee.getCountryName();
	}
	
	@Transient
	public String getExportCountryName() {
		
		return this.exporter.getCountryName();
	}
}
