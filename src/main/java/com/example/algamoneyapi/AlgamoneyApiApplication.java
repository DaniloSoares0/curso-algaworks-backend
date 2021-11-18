package com.example.algamoneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.example.algamoneyapi.config.property.AlgamoneyApiProperty;

@SpringBootApplication
@PropertySource("application-prod.properties")
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgamoneyApiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApiApplication.class, args);	
	}

}
