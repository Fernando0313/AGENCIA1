package idat.com.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.UsuarioEditar;
import idat.com.dto.request.UsuarioRegistro;
import idat.com.dto.response.RolDTO;
import idat.com.dto.response.UsuarioDTO;
import idat.com.dto.response.UsuarioLogin;
import idat.com.model.Rol;
import idat.com.model.Usuario;
import idat.com.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	
	@Autowired
	private  UsuarioRepository repo;
	
	
	
	
	@Autowired
	private RolServiceImpl rolServ;
	@Override
	public UsuarioDTO guardarUsuario(UsuarioRegistro usuarioDTO) {
		// TODO Auto-generated method stub
		UsuarioDTO usuarioRes = new UsuarioDTO();
		RolDTO rolDTO = rolServ.obtenerRol(usuarioDTO.getIdRolDTO());
		Rol rol = new Rol();
		rol.setEstado(rolDTO.getEstadoDTO());
		rol.setId(rolDTO.getIdDTO());
		rol.setRol(rolDTO.getRolDTO());
		
		Usuario usuario = new Usuario();
		usuario.setApMaterno(usuarioDTO.getApMaternoDTO());
		usuario.setApPaterno(usuarioDTO.getApPaternoDTO());
		usuario.setContrasena(usuarioDTO.getContrasenaDTO());
		usuario.setDocumento(usuarioDTO.getDocumentoDTO());
		usuario.setEmail(usuarioDTO.getEmailDTO());
		usuario.setNombre(usuarioDTO.getNombreDTO());
		usuario.setRoles(Collections.singleton(rol));
		usuario.setTelefono(usuarioDTO.getTelefonoDTO());
		Usuario _usuario = repo.save(usuario);
		usuarioRes.setApMaterno(_usuario.getApMaterno());
		usuarioRes.setApPaterno(_usuario.getApPaterno());
		usuarioRes.setDocumento(_usuario.getDocumento());
		usuarioRes.setEmail(_usuario.getEmail());
		usuarioRes.setNombre(_usuario.getNombre());

	
		//usuario.setRoles((List<Rol>) Collections.singleton(rol));
		
		
		return usuarioRes;
	}
	@Override
	public Optional<UsuarioLogin> obtenerDocumentoOEmail(String documento, String email) {
		// TODO Auto-generated method stub
		System.out.print(documento);
		Optional<Usuario> usuario = repo.findByDocumentoOrEmail(documento, email);
		
		UsuarioLogin Lusuario = new UsuarioLogin();
		Lusuario.setApMaternoDTO(usuario.get().getApMaterno());
		Lusuario.setApPaternoDTO(usuario.get().getApPaterno());
		Lusuario.setContrasenaDTO(usuario.get().getContrasena());
		Lusuario.setDocumentoDTO(usuario.get().getDocumento());
		Lusuario.setEmailDTO(usuario.get().getEmail());
		Lusuario.setNombreDTO(usuario.get().getNombre());
		Lusuario.setRolesDTO(usuario.get().getRoles());
		Lusuario.setIdDTO(usuario.get().getId());
		
		Optional<UsuarioLogin> Ousuario = Optional.of(Lusuario);
		return Ousuario;
	}
	@Override
	public Boolean existDocumento(String documento) {
		// TODO Auto-generated method stub
		
		Boolean _documento = repo.existsByDocumento(documento);
		return _documento;
	}
	@Override
	public Boolean existEmail(String email) {
		// TODO Auto-generated method stub
		Boolean _email = repo.existsByEmail(email);
		return _email;
	}
	@Override
	public UsuarioDTO editarUsuario(UsuarioEditar usuarioEditar) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		
		RolDTO rolDTO = rolServ.obtenerRol(usuarioEditar.getIdRolDTO());
		UsuarioDTO usuarioRes = new UsuarioDTO();
	
		
		usuario.setApMaterno(usuarioEditar.getApMaternoDTO());
		usuario.setApPaterno(usuarioEditar.getApPaternoDTO());
		usuario.setContrasena(usuarioEditar.getContrasenaDTO());
		usuario.setDocumento(usuarioEditar.getDocumentoDTO());
		usuario.setEmail(usuarioEditar.getEmailDTO());
		usuario.setNombre(usuarioEditar.getNombreDTO());
		
		if(rolDTO!=null) {
			Rol rol = new Rol();
			rol.setEstado(rolDTO.getEstadoDTO());
			rol.setId(rolDTO.getIdDTO());
			rol.setRol(rolDTO.getRolDTO());	
			usuario.setRoles(Collections.singleton(rol));
		}
		
		usuario.setTelefono(usuarioEditar.getTelefonoDTO());
		usuario.setId(usuarioEditar.getIdDTO());
		
		Usuario _usuario = repo.save(usuario);
		
		usuarioRes.setApMaterno(_usuario.getApMaterno());
		usuarioRes.setApPaterno(_usuario.getApPaterno());
		usuarioRes.setDocumento(_usuario.getDocumento());
		usuarioRes.setEmail(_usuario.getEmail());
		usuarioRes.setNombre(_usuario.getNombre());
		
		return usuarioRes;
	}
@Override
public UsuarioLogin buscarDocumento(String documento) {
	// TODO Auto-generated method stub
	Usuario _usuario = repo.findByDocumento(documento);
	UsuarioLogin usuarioRes = null;
if(_usuario!=null) {
	usuarioRes = new UsuarioLogin();
	usuarioRes.setApMaternoDTO(_usuario.getApMaterno());
	usuarioRes.setApPaternoDTO(_usuario.getApPaterno());
	usuarioRes.setDocumentoDTO(_usuario.getDocumento());
	usuarioRes.setEmailDTO(_usuario.getEmail());
	usuarioRes.setNombreDTO(_usuario.getNombre());
	usuarioRes.setIdDTO(_usuario.getId());
}
	
	return usuarioRes;
}
	@Override
	public UsuarioLogin buscarEmail(String email) {
		// TODO Auto-generated method stub
		
		Usuario _usuario = repo.findByEmail(email);
		UsuarioLogin usuarioRes = null;
		if(_usuario!=null) {
			 usuarioRes = new UsuarioLogin();
			usuarioRes.setApMaternoDTO(_usuario.getApMaterno());
		usuarioRes.setApPaternoDTO(_usuario.getApPaterno());
		usuarioRes.setDocumentoDTO(_usuario.getDocumento());
		usuarioRes.setEmailDTO(_usuario.getEmail());
		usuarioRes.setNombreDTO(_usuario.getNombre());
		usuarioRes.setIdDTO(_usuario.getId());
		}
		//System.out.println(_usuario.getApPaterno()+"$$$$$");
		
		return usuarioRes;
	}
}
