package com.neobpo.encurtador.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlModel {
	
	private  String id;	
	private String fullUrl;
	private LocalDateTime expiresAt;
	

}
