package com.example.algamoneyapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeradorSenha {

	public static void main(String[] args) {
	
		PasswordEncoder noOpPassowrd =  NoOpPasswordEncoder.getInstance();
		System.out.println(noOpPassowrd.encode("admin"));
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("@ngul@r0"));
	};
	
}

