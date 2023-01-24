package com.origin.certificate;

import java.io.IOException;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.origin.AbstractExporter;
import com.origin.entity.Certificate;

import jakarta.servlet.http.HttpServletResponse;

public class CertificateCsvExporter extends AbstractExporter{
	
	public void export(List<Certificate> listCertificates, HttpServletResponse response) throws IOException {
		
		super.setResponseHeader(response, "tetx/csv", ".csv", "certificates_");
				
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), 
				CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Certificate ID", "Serial Number", "Date", "Invoice", "Exporter", "Export Country", "Consignee", "Destination Country"};
		String[] fieldMapping = {"id", "serialNumber", "certificateDate", "invoiceData", "exporterName", "exportCountryName", "consigneeName", "destinationCountryName"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Certificate certificate : listCertificates) {
			csvWriter.write(certificate, fieldMapping);
		}
		
		csvWriter.close();
	}

}
