package com.neobpo.encurtador.service;

import java.net.URI;
import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.neobpo.encurtador.controller.dto.ShortenUrlRequest;
import com.neobpo.encurtador.entities.Url;
import com.neobpo.encurtador.model.UrlModel;
import com.neobpo.encurtador.repository.UrlRepository;

@Service
public class UrlService {
	
	final UrlRepository urlRepository;
	final ModelMapper mapper;

	public UrlService(UrlRepository urlRepository, ModelMapper mapper) {
		this.urlRepository = urlRepository;
		this.mapper = mapper;
	}
	
	public UrlModel shortenUrl(ShortenUrlRequest request){
		
		String id;		
		do {			
			id = RandomStringUtils.randomAlphanumeric(5,10);			
		}while(this.urlRepository.existsById(id));
		
		Url url = this.urlRepository.save(new Url( id , request.url() , LocalDateTime.now().plusMinutes(1) ));
		UrlModel urlModel = mapper.map(url,UrlModel.class);		
		return urlModel;
		
	} 
		
	public HttpHeaders redirect(String id){
		HttpHeaders headers = new HttpHeaders();
		var url = urlRepository.findById(id);
		
		if (url.isEmpty()) {
			return headers.EMPTY;
		}		
		headers.setLocation(URI.create(url.get().getFullUrl()));
		return headers;
		
	}	
	
	

}
