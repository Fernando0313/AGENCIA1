package idat.com.service;


import java.util.List;

import idat.com.dto.request.RolEditar;
import idat.com.dto.request.RolRegistro;
import idat.com.dto.response.RolDTO;


public interface RolService {

	public void guardarRol(RolRegistro rolRegistro);
	
	public void editarRol(RolEditar rolEditar);
	
	
	public void eliminarRol(Integer id);
	
	public List<RolDTO> listarRoles();
	public RolDTO obtenerRolId(Integer id);
	
	public Boolean existeRol(String rol);
}
