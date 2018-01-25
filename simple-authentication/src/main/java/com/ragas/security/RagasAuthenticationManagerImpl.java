/**
 * 
 */
package com.ragas.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Chandra Jagarlamudi
 *
 */
public class RagasAuthenticationManagerImpl implements AuthenticationManager {

	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("chandra").password("password").roles("ADMIN").build());
		manager.createUser(User.withUsername("lasya").password("password").roles("USER").build());
		return manager;
	}

	@SuppressWarnings("serial")
	public Authentication authenticate(Authentication auth) {
		UserDetails user = userDetailsService().loadUserByUsername(auth.getName());
		if (user != null) {
			if (user.getPassword().equals(auth.getCredentials())) {
				if (user.getUsername().equals("chandra")) {
					List<GrantedAuthority> gauthorites = new ArrayList<GrantedAuthority>();
					gauthorites.add(new SimpleGrantedAuthority("ADMIN"));
					return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(),  gauthorites);
				} else {
					System.out.println("User is" + user.getUsername());
					List<GrantedAuthority> gauthorites = new ArrayList<GrantedAuthority>();
					gauthorites.add(new SimpleGrantedAuthority("USER"));
					return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(),  gauthorites);
				}
			}
		}
		throw new BadCredentialsException("Bad Credentials");
	}
}
