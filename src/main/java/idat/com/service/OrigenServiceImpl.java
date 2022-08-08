package idat.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.OrigenEditar;
import idat.com.dto.request.OrigenRegistrar;
import idat.com.dto.response.OrigenDTO;
import idat.com.model.Origen;
import idat.com.model.Pais;
import idat.com.repository.OrigenRepository;
import idat.com.repository.PaisRepository;

@Service
public class OrigenServiceImpl implements OrigenService{
	
	@Autowired
	OrigenRepository repo;
	@Autowired 
	PaisRepository Prepo;

	@Override
	public void guardarOrigen(OrigenRegistrar origenR) {
		// TODO Auto-generated method stub
		Optional<Pais> pais= Prepo.findById(origenR.getIdPaisDTO()); 
		Origen origen = new Origen();
		origen.setNombre(origenR.getNombreDTO());
		origen.setPais(pais.get());
		repo.save(origen);
	}
@Override
public void editarOrigen(OrigenEditar origenR) {
	// TODO Auto-generated method stub
	
	Optional<Pais> pais= Prepo.findById(origenR.getIdPaisDTO()); 
	Origen origen = new Origen();
	origen.setId(origenR.getIdOrigenDTO());
	origen.setNombre(origenR.getNombreDTO());
	origen.setPais(pais.get());
	
	repo.saveAndFlush(origen);
	
}
	@Override
	public void eliminarOrigen(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public List<Origen> listarOrigen() {
		// TODO Auto-generated method stub
		/*List<Origen> origenList = new ArrayList<>();
		List<OrigenDTO> origenDTOList = new ArrayList<>();
		OrigenDTO origenDTO = null;
		for(Origen _origen : repo.findAll()) {
			System.out.println(_origen.getNombre());
			origenDTO = new OrigenDTO();
			origenDTO.setIdOrigenDTO(_origen.getId());
			origenDTO.setNombreDTO(_origen.getNombre());
			origenDTO.setPaisDTO(_origen.getPais());
			origenDTOList.add(origenDTO);
		}
		*/
		
		//System.out.println(origenList.get(0).getNombre());
		/*
		
		if(origenList.size()!=0) {
			for(Origen _origen : origenList) {
				origenDTO = new OrigenDTO();
				origenDTO.setIdOrigenDTO(_origen.getId());
				origenDTO.setNombreDTO(_origen.getNombre());
				origenDTO.setPaisDTO(_origen.getPais());
				origenDTOList.add(origenDTO);
			}
		}*/
		
		//System.out.println(origenList.get(0).getNombre());
		return repo.findAll();
	}

	@Override
	public OrigenDTO obtenerOrigen(Integer id) {
		// TODO Auto-generated method stub
		Optional<Origen> _origen = repo.findById(id);
		OrigenDTO origenDTO = new OrigenDTO();
		origenDTO.setIdOrigenDTO(_origen.get().getId());
		origenDTO.setNombreDTO(_origen.get().getNombre());
		origenDTO.setPaisDTO(_origen.get().getPais());
		return origenDTO;
	}

	@Override
	public Boolean existeOrigen(String nombre) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsBynombre(nombre);
		return bool;
	}

	@Override
	public Boolean existeOrigenId(Integer id) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsById(id);
		return bool;
	}

	@Override
	public OrigenDTO findByNombre(String nombre) {
		// TODO Auto-generated method stub
		Origen _origen = repo.findByNombre(nombre);
		OrigenDTO origenDTO = new OrigenDTO();
		origenDTO.setIdOrigenDTO(_origen.getId());
		origenDTO.setNombreDTO(_origen.getNombre());
		origenDTO.setPaisDTO(_origen.getPais());
		return origenDTO;
	}

}
