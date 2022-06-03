package idat.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.RolEditar;
import idat.com.dto.request.RolRegistro;
import idat.com.dto.response.RolDTO;
import idat.com.model.Rol;
import idat.com.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository repo;
	
	@Override
	public RolDTO obtenerRolId(Integer id) {
		// TODO Auto-generated method stub
		RolDTO rolDTO = null;
		
		Optional<Rol> rol = repo.findById(id); 
		if(rol.isEmpty()) {
			return rolDTO;
		}
		rolDTO = new RolDTO();
		rolDTO.setIdDTO(rol.get().getId());
		rolDTO.setEstadoDTO(rol.get().getEstado());
		rolDTO.setRolDTO(rol.get().getRol());
		
		return rolDTO;
	}

	@Override
	public void guardarRol(RolRegistro rolRegistro) {
		// TODO Auto-generated method stub
		Rol rol = new Rol();
		rol.setEstado(rolRegistro.getEstadoDTO());
		rol.setRol(rolRegistro.getNombreDTO());
		repo.save(rol);
		
	}

	@Override
	public void editarRol(RolEditar rolEditar) {
		// TODO Auto-generated method stub
		
		Rol rol = new Rol();
		rol.setEstado(rolEditar.getEstadoDTO());
		rol.setRol(rolEditar.getNombreDTO());
		rol.setId(rolEditar.getIdDTO());
		repo.saveAndFlush(rol);
		
	}

	@Override
	public void eliminarRol(Integer id) {
		// TODO Auto-generated method stub
		
		repo.deleteById(id);
		
	}

	@Override
	public List<RolDTO> listarRoles() {
		// TODO Auto-generated method stub
		List<Rol> rolList = repo.findAll();
		List<RolDTO> rolDTOList = new ArrayList<>();
		RolDTO rolDTO = null;
		for(Rol rol : rolList) {
			rolDTO = new RolDTO();
			rolDTO.setIdDTO(rol.getId());
			rolDTO.setEstadoDTO(rol.getEstado());
			rolDTO.setRolDTO(rol.getRol());
			rolDTOList.add(rolDTO);
		}
		
		return rolDTOList;
	}
	@Override
	public Boolean existeRol(String rol) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsByRol(rol);
		return bool;
	}

}
