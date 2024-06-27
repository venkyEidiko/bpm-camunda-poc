package com.bpm_camunda_service.pack.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bpm_camunda_service.pack.entity.Loan;
import com.bpm_camunda_service.pack.global_exception_handler.FilePathNotFoundException;
import com.bpm_camunda_service.pack.global_exception_handler.PdfGenerationException;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

@Service
public class PdfService {

	@Autowired
	private LoanService loanService;

	public ResponseEntity<InputStreamResource> generatePdf(List<String> keys)
			throws IOException, PdfGenerationException {

		try {
			List<Loan> loanData = loanService.getLoanData(keys);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(out);
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document document = new Document(pdfDoc, PageSize.A4);

			document.add(new Paragraph("Loan Request").setFontSize(16).setTextAlignment(TextAlignment.CENTER)
					.setFontColor(ColorConstants.BLACK));
			document.add(new Paragraph("\n"));

			Table table = new Table(7);

			table.addHeaderCell(
					new Cell().add(new Paragraph("BusinessKey").setTextAlignment(TextAlignment.CENTER).setPadding(5)));
			table.addHeaderCell(
					new Cell().add(new Paragraph("Name").setTextAlignment(TextAlignment.CENTER).setPadding(5)));
			table.addHeaderCell(
					new Cell().add(new Paragraph("Company").setTextAlignment(TextAlignment.CENTER).setPadding(5)));
			table.addHeaderCell(
					new Cell().add(new Paragraph("existingLoan").setTextAlignment(TextAlignment.CENTER).setPadding(5)));
			table.addHeaderCell(new Cell()
					.add(new Paragraph("newLoanAmount").setTextAlignment(TextAlignment.CENTER).setPadding(5)));
			table.addHeaderCell(
					new Cell().add(new Paragraph("ternure").setTextAlignment(TextAlignment.CENTER).setPadding(5)));
			table.addHeaderCell(new Cell()
					.add(new Paragraph("rateOfInterest").setTextAlignment(TextAlignment.CENTER).setPadding(5)));

			for (Loan loan : loanData) {
				table.addCell(
						new Cell().add(new Paragraph(loan.getBusinessKey()).setTextAlignment(TextAlignment.CENTER)));
				table.addCell(new Cell().add(new Paragraph(loan.getName()).setTextAlignment(TextAlignment.CENTER)));
				table.addCell(new Cell().add(new Paragraph(loan.getCompany()).setTextAlignment(TextAlignment.CENTER)));
				table.addCell(new Cell().add(
						new Paragraph(String.valueOf(loan.getExistingLoan())).setTextAlignment(TextAlignment.CENTER)));
				table.addCell(new Cell().add(
						new Paragraph(String.valueOf(loan.getNewLoanAmount())).setTextAlignment(TextAlignment.CENTER)));
				table.addCell(new Cell()
						.add(new Paragraph(String.valueOf(loan.getTernure())).setTextAlignment(TextAlignment.CENTER)));
				table.addCell(new Cell().add(new Paragraph(String.valueOf(loan.getRateOfInterest()))
						.setTextAlignment(TextAlignment.CENTER)));
			}

			document.add(table);
			document.close();

			byte[] pdf = out.toByteArray();

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
			String formattedDate = now.format(formatter);
			String entityName = "LOAN_REQUEST";
			String fileName = entityName + "_" + formattedDate + ".pdf";

			String directoryPath = "C:\\opt\\pdfGeneration";
			File directory = new File(directoryPath);
			if (!directory.exists()) {
				throw new FilePathNotFoundException("Directory path does not exist: " + directoryPath);
			}

			Path filePath = Paths.get(directoryPath, fileName);
			try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
				fos.write(pdf);
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", fileName);

			InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));

			return ResponseEntity.ok().headers(headers).body(resource);
		} catch (Exception e) {
			throw new PdfGenerationException("Error during PDF generation: " + e.getMessage());
		}
	}
}
