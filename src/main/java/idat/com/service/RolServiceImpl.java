package idat.com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.response.RolDTO;
import idat.com.model.Rol;
import idat.com.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository repo;
	
	@Override
	public RolDTO obtenerRol(Integer id) {
		// TODO Auto-generated method stub
		RolDTO rolDTO = new RolDTO();
		Optional<Rol> rol = repo.findById(id); 
		rolDTO.setIdDTO(rol.get().getId());
		rolDTO.setEstadoDTO(rol.get().getEstado());
		rolDTO.setRolDTO(rol.get().getRol());
		
		return rolDTO;
	}

}
