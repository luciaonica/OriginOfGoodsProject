package com.origin.exporter;

import java.io.IOException;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.origin.AbstractExporter;
import com.origin.entity.Exporter;

import jakarta.servlet.http.HttpServletResponse;

public class ExporterCsvExporter extends AbstractExporter{
	
	public void export(List<Exporter> listExporters, HttpServletResponse response) throws IOException {
		
		super.setResponseHeader(response, "tetx/csv", ".csv", "consignees_");
				
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), 
				CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Exporter ID", "Name", "Address", "Country"};
		String[] fieldMapping = {"id", "name", "address", "countryName"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Exporter exporter : listExporters) {
			csvWriter.write(exporter, fieldMapping);
		}
		
		csvWriter.close();
	}

}
