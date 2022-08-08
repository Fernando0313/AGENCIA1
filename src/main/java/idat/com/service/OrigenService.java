package idat.com.service;

import java.util.List;

import idat.com.dto.request.OrigenEditar;
import idat.com.dto.request.OrigenRegistrar;
import idat.com.dto.response.OrigenDTO;
import idat.com.model.Origen;



public interface OrigenService {

	public void guardarOrigen(OrigenRegistrar origenR);
	public void editarOrigen(OrigenEditar origenR);
	
	public void eliminarOrigen(Integer id);
	
	public List<Origen> listarOrigen();
	public OrigenDTO obtenerOrigen(Integer id);
	public Boolean existeOrigen(String nombre);
	public Boolean existeOrigenId(Integer id);
	public 	OrigenDTO findByNombre(String nombre);
}
