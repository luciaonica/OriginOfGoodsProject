package com.origin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "consignees")
public class Consignee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, nullable = false, unique = true)
	private String name;

	@Column(length = 64, nullable = false)
	private String address;	
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;	

	public Consignee(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Consignee(String name, String address, Country country) {
		this.name = name;
		this.address = address;
		this.country = country;
	}
	
	public Consignee() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Consignee [id=" + id + ", name=" + name + ", address=" + address + ", country=" + country + "]";
	}	
	
	@Transient
	public String getCountryName() {
		
		return this.country.getName();
	}
}
