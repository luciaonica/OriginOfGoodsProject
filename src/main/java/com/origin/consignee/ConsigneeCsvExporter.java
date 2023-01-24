package com.origin.consignee;

import java.io.IOException;
import java.util.List;


import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.origin.AbstractExporter;
import com.origin.entity.Consignee;

import jakarta.servlet.http.HttpServletResponse;

public class ConsigneeCsvExporter extends AbstractExporter{
	
	public void export(List<Consignee> listConsignees, HttpServletResponse response) throws IOException {
		
		super.setResponseHeader(response, "tetx/csv", ".csv", "consignees_");
				
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), 
				CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Consignee ID", "Name", "Address", "Country"};
		String[] fieldMapping = {"id", "name", "address", "countryName"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Consignee consignee : listConsignees) {
			csvWriter.write(consignee, fieldMapping);
		}
		
		csvWriter.close();
	}

}
