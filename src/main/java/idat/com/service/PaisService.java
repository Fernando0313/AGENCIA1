package idat.com.service;

import java.util.List;



import idat.com.dto.PaisDTORequest;
import idat.com.dto.PaisDTOResponse;

public interface PaisService {

	
	public void guardarPais(PaisDTORequest pais);
	public void editarPais(PaisDTORequest pais);
	
	public void eliminarPais(Integer id);
	
	public List<PaisDTOResponse> listarPais();
	public PaisDTOResponse obtenerPais(Integer id);
}
