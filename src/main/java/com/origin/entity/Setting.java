package com.origin.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "settings")
public class Setting {
	@Id
	@Column(name = "`key`", nullable = false, length = 128)
	private String key;
	
	@Column(nullable = false, length = 1024)
	private String value;	
	
	public Setting() {
	}
	
	public Setting(String key) {
		this.key = key;
	}

	public Setting(String key, String value) {
		this.key = key;
		this.value = value;		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setting other = (Setting) obj;
		return Objects.equals(key, other.key);
	}

	@Override
	public String toString() {
		return "Setting [key=" + key + ", value=" + value;
	}

}
