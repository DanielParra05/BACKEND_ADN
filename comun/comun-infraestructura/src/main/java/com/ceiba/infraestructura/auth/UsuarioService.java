package com.ceiba.infraestructura.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.infraestructura.auth.user.DaoUserMySql;

@Service
public class UsuarioService implements UserDetailsService {
	
	private static final String USER_NOT_FOUND= "El usuario no existe en la base de datos";
	private static final List<GrantedAuthority> authorities = new ArrayList<>(
			Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")));
	
	@Autowired
	private DaoUserMySql daoUser;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		String pass = daoUser.listar().get(username);		
		if (pass == null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND);
		}
		return new User(username, pass, true, true, true,
				true, authorities);

	}

}
