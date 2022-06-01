package idat.com.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.PaisDTORequest;
import idat.com.dto.response.PaisDTOResponse;
import idat.com.model.Pais;
import idat.com.repository.PaisRepository;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	private PaisRepository repo;
	@Override
	public PaisDTOResponse guardarPais(PaisDTORequest pais) {
		// TODO Auto-generated method stub
		Pais pais2 = new Pais();
		pais2.setNombre(pais.getNombreDTO());
		Pais pais3 = repo.save(pais2);
		
		PaisDTOResponse resp = new PaisDTOResponse();
		resp.setNombreDTO(pais3.getNombre());
		return resp;
	}

	@Override
	public void editarPais(PaisDTORequest pais) {
		// TODO Auto-generated method stub
		Pais pais1 = new Pais();
		
		pais1.setNombre(pais.getNombreDTO());
		pais1.setId_pais(pais.getIdPaisDTO());
		
		repo.saveAndFlush(pais1);
		
	}

	@Override
	public void eliminarPais(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public List<PaisDTOResponse> listarPais() {
		// TODO Auto-generated method stub
		/*
		List<CiudadPais> lCiudad = new ArrayList<>();
		PaisDTOResponse paisD= null;
		List<PaisDTOResponse> paisR = new ArrayList<>();
		List<Pais> lPais = new ArrayList<>();
		
		
		repo.findAll().forEach(lPais::add);
		CiudadPais c =null;
		
		
		for(Pais p : lPais) {
			paisD = new PaisDTOResponse();
			System.out.println(p.getNombre()+"-"+p.getCiudad().size());
			paisD.setIdPaisDTO(p.getId_pais());
			paisD.setNombreDTO(p.getNombre());
			//p.getCiudad().forEach(lCiudad::add);
			for(Ciudad ciudad: p.getCiudad()) {
				c=new CiudadPais();
				c.setNombre(ciudad.getNombre());
				c.setIdCiudadDTO(ciudad.getId_ciudad());
				lCiudad.add(c);
			}
			paisD.setCiudadList(lCiudad);
		
			
		}
		*/

		
		PaisDTOResponse resp = null;
		List<PaisDTOResponse>paisList = new ArrayList<>();
		
		
		List<Pais> lPais = new ArrayList<>();
		repo.findAll().forEach(lPais::add);
		for(Pais p : lPais) {
			resp =new PaisDTOResponse();
			
			resp.setIdPaisDTO(p.getId_pais());
			resp.setNombreDTO(p.getNombre());
			resp.setCiudad(p.getCiudad());
			paisList.add(resp);
			
		}
		
		
		
		return paisList;
	}

	@Override
	public PaisDTOResponse obtenerPais(Integer id) {
		// TODO Auto-generated method stub
		PaisDTOResponse paisDTO = new PaisDTOResponse();
		Pais pais = repo.findById(id).orElse(null);
		
		if(pais==null) {
			return null;
		}
		paisDTO.setCiudad(pais.getCiudad());
		paisDTO.setIdPaisDTO(pais.getId_pais());
		paisDTO.setNombreDTO(pais.getNombre());

		return paisDTO;
	}

	

}
