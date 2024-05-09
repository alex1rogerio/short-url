package com.neobpo.encurtador.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neobpo.encurtador.controller.dto.ShortenUrlRequest;
import com.neobpo.encurtador.controller.dto.ShortenUrlResponse;
import com.neobpo.encurtador.model.UrlModel;
import com.neobpo.encurtador.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UrlController {
	
	private final UrlService urlService; 
	
	private static final String SHORTEN_URL = "shorten-url"; 
	
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}

	@PostMapping(value = "/"+SHORTEN_URL)
	public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request , HttpServletRequest servletRequest){
		
		UrlModel shortenUrl = urlService.shortenUrl(request);
		var redirectUrl = servletRequest.getRequestURL().toString().replace(SHORTEN_URL, shortenUrl.getId() );		
		return ResponseEntity.ok( new ShortenUrlResponse(redirectUrl));		
	}	
	
	
	@GetMapping("{id}")
	public ResponseEntity<Void> redirect(@PathVariable(name="id") String id){

		HttpHeaders headers = urlService.redirect(id);
		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
		
	}
	

}
