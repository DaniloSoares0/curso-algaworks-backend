package com.example.algamoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

	private String originPermitida = "http://localhost:8000";

	private final Seguranca seguranca = new Seguranca();
	
	@Data
	public static class Seguranca {

		private boolean enableHttps;

	}
	
	
}