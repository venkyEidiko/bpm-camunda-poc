package com.bpm_camunda_service.pack.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpm_camunda_service.pack.global_exception_handler.InputFieldsRequiredException;
import com.bpm_camunda_service.pack.global_exception_handler.PdfGenerationException;
import com.bpm_camunda_service.pack.service.PdfService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PdfControllor {

	private final PdfService pdfService;

	@GetMapping("/pdf")
	public ResponseEntity<String> pdfDownload(@RequestParam("businessKey") List<String> businesskeyList)
			throws IOException, PdfGenerationException, InputFieldsRequiredException {

		if (businesskeyList.isEmpty()) {
			throw new InputFieldsRequiredException("Required input fields ");
		} else {
			try {
				ResponseEntity<InputStreamResource> pdf = pdfService.generatePdf(businesskeyList);
				if (pdf.getStatusCode() == HttpStatus.OK) {
					return new ResponseEntity<>("pdf is generated", HttpStatus.OK);

				} else {
					throw new PdfGenerationException("Failed to generate PDF");
				}
			} catch (Exception e) {
				throw new PdfGenerationException("Failed to generate PDF: " + e.getMessage());
			}
		}
	}

}
