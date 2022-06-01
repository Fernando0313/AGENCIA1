package idat.com.service;


import java.util.List;
import java.util.Optional;

import idat.com.dto.request.UsuarioEditar;
import idat.com.dto.request.UsuarioRegistro;
import idat.com.dto.response.UsuarioDTO;
import idat.com.dto.response.UsuarioLogin;

public interface UsuarioService {

	public UsuarioDTO guardarUsuario(UsuarioRegistro usuarioDTO);
	
	public Optional<UsuarioLogin> obtenerDocumentoOEmail(String documento, String email );
	
	public Boolean existDocumento(String documento);
	public Boolean existEmail(String email);
	
	public UsuarioDTO editarUsuario(UsuarioEditar usuarioEditar);
	public UsuarioLogin buscarPorId(Integer id);
	
	public UsuarioLogin buscarDocumento(String documento);
	public UsuarioLogin buscarEmail(String email);
	
	public List<UsuarioLogin> listarUsuarios();
	
	//public void eliminarPais(Integer id);
	
	//public List<PaisDTOResponse> listarPais();
	//public PaisDTOResponse obtenerPais(Integer id);
}
