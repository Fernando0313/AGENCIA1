package idat.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.CiudadDTORequest;
import idat.com.dto.response.CiudadDTOResponse;
import idat.com.model.Ciudad;
import idat.com.model.Pais;
import idat.com.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements CiudadService{

	@Autowired
	CiudadRepository repo;

	@Override
	public CiudadDTOResponse guardarCiudad(CiudadDTORequest ciudadR) {
		// TODO Auto-generated method stub
		Ciudad ciudad = new Ciudad();
		Ciudad ciudad2 = new Ciudad();
		Pais pais = new Pais();
		CiudadDTOResponse resp = new CiudadDTOResponse();
		
		pais.setId_pais(ciudadR.getId_paisDTO());
		ciudad.setNombre(ciudadR.getNombreDTO());
		ciudad.setPais(pais);
		ciudad2 = repo.save(ciudad);
		//System.out.println(ciudad2.getPais().getId_pais())
		resp.setNombreDTO(ciudad2.getNombre());
		resp.setId_paisDTO(ciudad2.getPais().getId_pais());
		
		
		
		return resp;
	}

	@Override
	public void editarCiudad(CiudadDTORequest ciudadR) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCiudad(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CiudadDTOResponse> listarCiudad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiudadDTOResponse obtenerCiudad(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
