package com.ceiba.infraestructura.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

	private static final List<GrantedAuthority> authorities = new ArrayList<>(
			Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")));

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Debe ir encriptada la pass
		return new User("daniel", "$2a$10$F/PNBYT1A6mg60BVQbqWAeDQKuO3mQ8ExOT5ymj3.dd5MzTL88xhy", true, true, true,
				true, authorities);

	}

}
