package com.df.commonservice;


import com.df.commonmodel.models.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;


@RestControllerAdvice
public class FactoryExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { MalformedURLException.class, InterruptedException.class, ExecutionException.class })
	protected ResponseEntity<Object> handleCommonException(RuntimeException e, WebRequest request) {
		ApiResponse<String> apiError = new ApiResponse<>(HttpStatus.BAD_REQUEST, e);
		return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}


	@ExceptionHandler(value = UnknownHostException.class)
	protected ResponseEntity<Object> handleUnknowHostException(RuntimeException e, WebRequest request) {
		ApiResponse<String> apiError = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot connect to server", e);
		return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(value = IOException.class)
	protected ResponseEntity<Object> handleIOException(RuntimeException e, WebRequest request) {
		ApiResponse<String> apiError = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e);
		return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
