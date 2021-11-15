package com.example.algamoneyapi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{

	private static final long serialVersionUID = -4998356362045184911L;

	private Usuario user;

	public UserPrincipal(Usuario user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
		// TODO Auto-generated method stub
	}

	@Override
	public String getUsername() {
		return null;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.getActive() == 1;
	}

}
