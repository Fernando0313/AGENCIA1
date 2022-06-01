package idat.com.service;

import java.util.List;

import idat.com.dto.request.PaisDTORequest;
import idat.com.dto.response.PaisDTOResponse;

public interface PaisService {

	
	public PaisDTOResponse guardarPais(PaisDTORequest pais);
	public void editarPais(PaisDTORequest pais);
	
	public void eliminarPais(Integer id);
	
	public List<PaisDTOResponse> listarPais();
	public PaisDTOResponse obtenerPais(Integer id);
}
