package idat.com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.UsuarioEditar;
import idat.com.dto.request.UsuarioRegistro;
import idat.com.dto.response.AuthDTO;
import idat.com.dto.response.UsuarioDTO;
import idat.com.dto.response.UsuarioLogin;
import idat.com.model.Empresa;
import idat.com.model.Rol;
import idat.com.model.TipoDocumento;
import idat.com.model.Usuario;
import idat.com.repository.EmpresaRepository;
import idat.com.repository.RolRepository;
import idat.com.repository.TipoDocumentoRepository;
import idat.com.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	
	@Autowired
	private  UsuarioRepository repo;
	@Autowired 
	private EmpresaRepository Erepo;
	@Autowired
	private TipoDocumentoRepository Trepo; 
	@Autowired
	private RolRepository Rrepo;
	
	@Override
	public UsuarioDTO guardarUsuario(UsuarioRegistro usuarioDTO) {
		// TODO Auto-generated method stub
		UsuarioDTO usuarioRes = new UsuarioDTO();
		Optional<Rol> rol = Rrepo.findById(usuarioDTO.getIdRolDTO());
		Optional<TipoDocumento> tipo = Trepo.findById(usuarioDTO.getIdTipoDocumento());
		Optional<Empresa> empresa = Erepo.findById(usuarioDTO.getIdEmpresaDTO());
		
		
		Usuario usuario = new Usuario();
		usuario.setApMaterno(usuarioDTO.getApMaternoDTO());
		usuario.setApPaterno(usuarioDTO.getApPaternoDTO());
		usuario.setContrasena(usuarioDTO.getContrasenaDTO());
		usuario.setDocumento(usuarioDTO.getDocumentoDTO());
		usuario.setEmail(usuarioDTO.getEmailDTO());
		usuario.setNombre(usuarioDTO.getNombreDTO());
		usuario.setRoles(Collections.singleton(rol.get()));
		usuario.setTelefono(usuarioDTO.getTelefonoDTO());
		usuario.setEmpresa(empresa.get());
		usuario.setTipoDocumento(tipo.get());
		usuario.setEstado(usuarioDTO.getEstadoDTO());
		Usuario _usuario = repo.save(usuario);
		usuarioRes.setApMaterno(_usuario.getApMaterno());
		usuarioRes.setApPaterno(_usuario.getApPaterno());
		usuarioRes.setDocumento(_usuario.getDocumento());
		usuarioRes.setEmail(_usuario.getEmail());
		usuarioRes.setNombre(_usuario.getNombre());
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
		
		System.out.println("aeaaaaaaaaaaaaaaaaaa");
		String nombre= usuarioEditar.getNombreDTO();
		System.out.println("aeaaaaaaaaaaaaaaaaaa");
		String apPaterno= usuarioEditar.getApPaternoDTO();
		String apMaterno = usuarioEditar.getApMaternoDTO();
		String contrasena = usuarioEditar.getContrasenaDTO();
		String documento = usuarioEditar.getDocumentoDTO();
		String telefono = usuarioEditar.getTelefonoDTO();
		String email = usuarioEditar.getEmailDTO();
		String estado = usuarioEditar.getEstadoDTO();
		Integer idUsuario = usuarioEditar.getIdDTO();
		Integer idEmpresa = usuarioEditar.getIdEmpresaDTO();
		Integer idTipo = usuarioEditar.getIdTipoDocumento();
		Integer idRol = usuarioEditar.getIdRolDTO();
		
		Usuario usuario = new Usuario();
		System.out.println("aeaaaaaaaaaaaaaaaaaa");
		if(idTipo!=null) {
			Optional<TipoDocumento> tipo = Trepo.findById(idTipo);
			usuario.setTipoDocumento(tipo.get());
		}
		if(idEmpresa!=null) {
			Optional<Empresa> empresa = Erepo.findById(idEmpresa);
			usuario.setEmpresa(empresa.get());
		}
		if(idRol!=null) {
			Optional<Rol> rol = Rrepo.findById(idRol);
			usuario.setRoles(Collections.singleton(rol.get()));
		}
		System.out.println("aeaaaaaaaaaaaaaaaaaa");
		Optional<Usuario> _usuariof = repo.findById(usuarioEditar.getIdDTO());
		
		
		UsuarioDTO usuarioRes = new UsuarioDTO();
	
		
		if(nombre==null) {
			System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
			usuario.setNombre(_usuariof.get().getNombre());
		}else {
			usuario.setNombre(nombre);
		}
		
		if(apPaterno==null) {
			
			usuario.setApPaterno(_usuariof.get().getApPaterno());
		}else {
			System.out.println(apPaterno);
			usuario.setApPaterno(apPaterno);
		}
		
		if(apMaterno==null) {
			usuario.setApMaterno(_usuariof.get().getApMaterno());
		}else {
			usuario.setApMaterno(apMaterno);
		}
		
		if(contrasena==null) {
			usuario.setContrasena(_usuariof.get().getContrasena());
		}else {
			usuario.setContrasena(contrasena);
		}
		
		if(documento==null) {
			usuario.setDocumento(_usuariof.get().getDocumento());
		}else {
			usuario.setDocumento(documento);
		}
		
		if(telefono==null) {
			usuario.setTelefono(_usuariof.get().getTelefono());
		}else {
			usuario.setTelefono(telefono);
		}
		if(email==null) {
			usuario.setEmail(_usuariof.get().getEmail());
		}else {
			usuario.setEmail(email);
		}
		if(idEmpresa==null) {
			usuario.setEmpresa(_usuariof.get().getEmpresa());
		}
		if(idTipo==null) {
			usuario.setTipoDocumento(_usuariof.get().getTipoDocumento());
		}
		if(idRol==null) {
			usuario.setRoles(_usuariof.get().getRoles());
		}
		if(estado==null) {
			usuario.setEstado(_usuariof.get().getEstado());
		}else {
			usuario.setEstado(estado);
		}
		usuario.setId(idUsuario);
		Usuario _usuario = repo.saveAndFlush(usuario);
		
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
	
	@Override
	public UsuarioLogin buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Usuario> usuario = repo.findById(id);
		UsuarioLogin usuarioLogin = null;
		if(!usuario.isEmpty()) {
			System.out.println("##################333");
			usuarioLogin = new UsuarioLogin();
			usuarioLogin.setApMaternoDTO(usuario.get().getApMaterno());
			usuarioLogin.setApPaternoDTO(usuario.get().getApPaterno());
			usuarioLogin.setDocumentoDTO(usuario.get().getDocumento());
			usuarioLogin.setEmailDTO(usuario.get().getEmail());
			usuarioLogin.setIdDTO(usuario.get().getId());
			usuarioLogin.setNombreDTO(usuario.get().getNombre());
			usuarioLogin.setRolesDTO(usuario.get().getRoles());
			usuarioLogin.setTelefonoDTO(usuario.get().getTelefono());
		}
		
		return usuarioLogin;
	}
	
	@Override
	public List<UsuarioLogin> listarUsuarios() {
		
		List<Usuario> usuarioList = repo.findAll();
		List<UsuarioLogin> usuarioLoginList = new ArrayList<UsuarioLogin>();
		UsuarioLogin usuarioLogin = null;
		if(usuarioList.size()!=0) {
			for(Usuario usuario : usuarioList) {
				 usuarioLogin = new UsuarioLogin();
				 usuarioLogin.setApMaternoDTO(usuario.getApMaterno());
					usuarioLogin.setApPaternoDTO(usuario.getApPaterno());
					usuarioLogin.setDocumentoDTO(usuario.getDocumento());
					usuarioLogin.setEmailDTO(usuario.getEmail());
					usuarioLogin.setIdDTO(usuario.getId());
					usuarioLogin.setNombreDTO(usuario.getNombre());
					usuarioLogin.setRolesDTO(usuario.getRoles());
					usuarioLogin.setTelefonoDTO(usuario.getTelefono());
					usuarioLoginList.add(usuarioLogin);
			}
		}
		
		
		
		// TODO Auto-generated method stub
		return usuarioLoginList;
	}
	@Override
	public Optional<AuthDTO> obtenerDocumentoOEmailAuth(String documento, String email) {
		// TODO Auto-generated method stub
		Optional<Usuario> usuario = repo.findByDocumentoOrEmail(documento, email);
		AuthDTO authDTO = new AuthDTO();
		authDTO.setContrasenaDTO(usuario.get().getContrasena());
		authDTO.setEmailDTO(usuario.get().getEmail());
		authDTO.setRolSetDTO(usuario.get().getRoles());
		Optional<AuthDTO> Authusuario = Optional.of(authDTO);
		return Authusuario;
	}
	
	@Override
	public Boolean existsId(Integer id) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsById(id);
		return bool;
	}
	@Override
	public void eliminarUsuario(Integer id) {
		// TODO Auto-generated method stub
		
		repo.deleteById(id);
	}
}
