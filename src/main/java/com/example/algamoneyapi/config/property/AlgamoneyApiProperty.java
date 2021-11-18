package com.example.algamoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

	private final Seguranca seguranca = new Seguranca();
	
	@Data
	public static class Seguranca{
		private boolean enableHttps;
	}
}
