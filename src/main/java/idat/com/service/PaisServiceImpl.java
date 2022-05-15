package idat.com.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.PaisDTORequest;
import idat.com.dto.PaisDTOResponse;
import idat.com.model.Pais;
import idat.com.repository.PaisRepository;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	private PaisRepository repo;
	@Override
	public void guardarPais(PaisDTORequest pais) {
		// TODO Auto-generated method stub
		Pais pais2 = new Pais();
		pais2.setNombre(pais.getNombreDTO());
		repo.save(pais2);
	}

	@Override
	public void editarPais(PaisDTORequest pais) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPais(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaisDTOResponse> listarPais() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaisDTOResponse obtenerPais(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
