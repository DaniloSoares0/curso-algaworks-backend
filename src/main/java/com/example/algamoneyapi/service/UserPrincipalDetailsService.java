package com.example.algamoneyapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Usuario;
import com.example.algamoneyapi.model.UserPrincipal;

//@Service("userDetailsService")
public class UserPrincipalDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Usuario user = null;// Usuario.builder().password("admin").username("admian").active(1).roles("ROLE").build();
		UserPrincipal userPrincipal = new UserPrincipal(user);
		
		return userPrincipal;
	}

}
