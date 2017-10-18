package com.wishlister.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		log.debug("Autenticando o usuário: ", login);

//		User user = userRepository.findByLogin(login);
//		
//		if (user == null) {
//			throw new UsernameNotFoundException("Usuário " + login + " não existe!");
//		} 
//
//		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//		for (Role role : user.getRoles()) {
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//			grantedAuthorities.add(grantedAuthority);
//		}

//		return new org.springframework.security.core.userdetails.User(login, user.getPassword(), grantedAuthorities);
		return null;
	}

	
}