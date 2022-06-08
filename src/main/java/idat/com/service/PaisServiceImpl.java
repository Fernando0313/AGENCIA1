package idat.com.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		pais1.setId(pais.getIdPaisDTO());
		
		repo.saveAndFlush(pais1);
		
	}

	@Override
	public void eliminarPais(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public List<PaisDTOResponse> listarPais() {
		
	
		
		List<Pais> lPais = new ArrayList<>();
		List<PaisDTOResponse> paisListDTO = new ArrayList<PaisDTOResponse>();
		PaisDTOResponse paisDTO = null;
		repo.findAll().forEach(lPais::add);
		
		if(lPais.size()!=0) {
			for(Pais pais : lPais) {
					paisDTO = new PaisDTOResponse();
					paisDTO.setIdPaisDTO(pais.getId());
					paisDTO.setCiudadDTO(pais.getCiudad());
					paisDTO.setDestinoDTO(pais.getDestino());
					paisDTO.setOrigenDTO(pais.getOrigen());
					paisDTO.setNombreDTO(pais.getNombre());
					paisListDTO.add(paisDTO);
			}
		}
		
		

	return paisListDTO;
			
		}
		
	

	@Override
	public PaisDTOResponse obtenerPais(Integer id) {
		// TODO Auto-generated method stub
		PaisDTOResponse paisDTO = new PaisDTOResponse();
		Optional<Pais> pais = repo.findById(id);
		if(pais==null) {
			return null;
		}
		paisDTO.setIdPaisDTO(pais.get().getId());
		paisDTO.setCiudadDTO(pais.get().getCiudad());
		paisDTO.setDestinoDTO(pais.get().getDestino());
		paisDTO.setOrigenDTO(pais.get().getOrigen());
		paisDTO.setNombreDTO(pais.get().getNombre());
		return paisDTO;
	}

	@Override
	public Boolean existePais(String nombre) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsByNombre(nombre);
		return bool;
	}
	
	@Override
	public Boolean existePaisId(Integer id) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsById(id);
		return bool;
	}
	@Override
	public PaisDTOResponse findByNombre(String nombre) {
		// TODO Auto-generated method stub
		PaisDTOResponse paisDTO = new PaisDTOResponse();
		Pais pais = repo.findByNombre(nombre);
		if(pais==null) {
			return null;
		}
		paisDTO.setIdPaisDTO(pais.getId());
		paisDTO.setCiudadDTO(pais.getCiudad());
		paisDTO.setDestinoDTO(pais.getDestino());
		paisDTO.setOrigenDTO(pais.getOrigen());
		paisDTO.setNombreDTO(pais.getNombre());
		return paisDTO;
		
	}
}
