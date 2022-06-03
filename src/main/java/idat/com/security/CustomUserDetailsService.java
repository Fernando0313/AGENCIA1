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

import idat.com.dto.response.AuthDTO;
import idat.com.model.Rol;
import idat.com.service.UsuarioServiceImpl;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioServiceImpl Uservice;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		
		AuthDTO Lusuario = Uservice.obtenerDocumentoOEmailAuth(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));
		return new User(Lusuario.getEmailDTO(), Lusuario.getContrasenaDTO(), mapearRoles(Lusuario.getRolSetDTO()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> list){
		return list.stream().map(rol -> new SimpleGrantedAuthority(rol.getRol())).collect(Collectors.toList());
	}
}
