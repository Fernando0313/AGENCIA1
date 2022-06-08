package idat.com.service;

import java.util.List;

import idat.com.dto.request.OrigenRequest;
import idat.com.dto.response.OrigenDTO;



public interface OrigenService {

	public void guardarOrigen(OrigenRequest origenR);
	public void editarOrigen(OrigenRequest origenR);
	
	public void eliminarOrigen(Integer id);
	
	public List<OrigenDTO> listarOrigen();
	public OrigenDTO obtenerPais(Integer id);
	public Boolean existeOrigen(String nombre);
	public Boolean existeOrigenId(Integer id);
	public 	OrigenDTO findByNombre(String nombre);
}
