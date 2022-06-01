package idat.com.service;


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
	
	
	public UsuarioLogin buscarDocumento(String documento);
	public UsuarioLogin buscarEmail(String email);
	//public void eliminarPais(Integer id);
	
	//public List<PaisDTOResponse> listarPais();
	//public PaisDTOResponse obtenerPais(Integer id);
}
