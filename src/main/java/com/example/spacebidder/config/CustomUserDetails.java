package com.example.spacebidder.config;

import java.util.Collection;
import java.util.Collections;

import com.example.spacebidder.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;




public class CustomUserDetails implements UserDetails{
	
	
	private User user;
	
	public CustomUserDetails(User user)
	{
		super();
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority("admin"));
	}

	@Override
	public String getPassword() {
		
		return user.getClientPass();
	}

	@Override
	public String getUsername() {
		
		return user.getClientEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
