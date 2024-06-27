package com.bpm_camunda_service.pack.global_exception_handler;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ProblemDetail controllerExceptionHandler(Exception exception) {
		ProblemDetail errorDetail = null;

		exception.printStackTrace();

		if (exception instanceof UserPrincipalNotFoundException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
			errorDetail.setProperty("description", "The username or password is incorrect");

		} else if (exception instanceof FilePathNotFoundException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
			errorDetail.setProperty("description", "The specified directory path does not exist");
		} else if (exception instanceof PdfGenerationException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
			errorDetail.setProperty("description", "Error generating PDF");
		}
		else if (exception instanceof InputFieldsRequiredException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
			errorDetail.setProperty("description", "Required input fields");
		}
		 else {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
			errorDetail.setProperty("description", "Internal server error");
		}

		return errorDetail;
	}

}
