package idat.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.EmpresaEditar;
import idat.com.dto.request.EmpresaRegistrar;
import idat.com.dto.response.EmpresaDTO;
import idat.com.model.Empresa;
import idat.com.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository repo;

	@Override
	public Boolean existeId(Integer id) {
		// TODO Auto-generated method stub
		boolean boll = repo.existsById(id);
		
		return boll;
	}

	@Override
	public void guardarEmpresa(EmpresaRegistrar empresaRegistrar) {
		// TODO Auto-generated method stub
		Empresa empresa = new Empresa();
		empresa.setEstado(empresaRegistrar.getEstadoDTO());
		empresa.setNombre(empresaRegistrar.getNombreDTO());
		empresa.setRazonSocial(empresaRegistrar.getRazonSocialDTO());
		repo.save(empresa);
		
	}

	@Override
	public void editarEmpresa(EmpresaEditar empresaEditar) {
		// TODO Auto-generated method stub
		
		Empresa empresa = new Empresa();
		empresa.setEstado(empresaEditar.getEstadoDTO());
		empresa.setNombre(empresaEditar.getNombreDTO());
		empresa.setRazonSocial(empresaEditar.getRazonSocialDTO());
		empresa.setId(empresaEditar.getIdDTO());
		repo.saveAndFlush(empresa);
	}

	@Override
	public List<EmpresaDTO> listarEmpresas() {
		// TODO Auto-generated method stub
		
		List<Empresa> empresaList = repo.findAll();
		List<EmpresaDTO> empresaDTOList = new ArrayList<>();
		EmpresaDTO empresaDTO = null;
		if(empresaList.size()!=0) {
			for(Empresa empresa : empresaList) {
				empresaDTO = new EmpresaDTO();
				empresaDTO.setEstadoDTO(empresa.getEstado());
				empresaDTO.setIdDTO(empresa.getId());
				empresaDTO.setNombreDTO(empresa.getNombre());
				empresaDTO.setRazonSocialDTO(empresa.getRazonSocial());
				empresaDTOList.add(empresaDTO);
			}
		}
		
		
		return empresaDTOList;
	}

	@Override
	public EmpresaDTO buscarId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Empresa> empresa = repo.findById(id);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setEstadoDTO(empresa.get().getEstado());
		empresaDTO.setIdDTO(empresa.get().getId());
		empresaDTO.setNombreDTO(empresa.get().getNombre());
		empresaDTO.setRazonSocialDTO(empresa.get().getRazonSocial());
		return empresaDTO;
	}

	@Override
	public void eliminarEmpresa(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}
	@Override
	public Boolean existeEmpresa(String empresa) {
		// TODO Auto-generated method stub
		boolean bool = repo.existsByNombre(empresa);
		return bool;
	}
	
}
