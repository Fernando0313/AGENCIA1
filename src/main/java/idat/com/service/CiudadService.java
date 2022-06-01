package idat.com.service;

import java.util.List;

import idat.com.dto.request.CiudadDTORequest;
import idat.com.dto.response.CiudadDTOResponse;

public interface CiudadService {

	
	public CiudadDTOResponse guardarCiudad(CiudadDTORequest ciudadR);
	public void editarCiudad(CiudadDTORequest ciudadR);
	
	public void eliminarCiudad(Integer id);
	
	public List<CiudadDTOResponse> listarCiudad();
	public CiudadDTOResponse obtenerCiudad(Integer id);
}
