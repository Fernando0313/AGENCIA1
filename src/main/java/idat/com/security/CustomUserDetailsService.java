package idat.com.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import idat.com.dto.response.UsuarioLogin;
import idat.com.model.Rol;
import idat.com.model.Usuario;
import idat.com.repository.UsuarioRepository;
import idat.com.service.UsuarioServiceImpl;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioServiceImpl Uservice;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		
		UsuarioLogin Lusuario = Uservice.obtenerDocumentoOEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));
	
		
		System.out.println(Lusuario.getRolesDTO());
		return new User(Lusuario.getEmailDTO(), Lusuario.getContrasenaDTO(), mapearRoles(Lusuario.getRolesDTO()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> list){
		
		return list.stream().map(rol -> new SimpleGrantedAuthority(rol.getRol())).collect(Collectors.toList());
	}
}
