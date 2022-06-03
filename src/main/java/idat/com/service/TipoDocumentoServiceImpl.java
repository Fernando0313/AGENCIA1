package idat.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.dto.request.TipoDocumentoEditar;
import idat.com.dto.request.TipoDocumentoRegistro;
import idat.com.dto.response.TipoDocumentoDTO;
import idat.com.model.TipoDocumento;
import idat.com.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{

	@Autowired
	private TipoDocumentoRepository repo;
	
	@Override
	public Boolean existeId(Integer id) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsById(id);
		return bool;
	}

	@Override
	public Boolean existeTipo(String tipo) {
		// TODO Auto-generated method stub
		Boolean bool = repo.existsByTipo(tipo);
		return bool;
	}

	@Override
	public void registrarTipo(TipoDocumentoRegistro registro) {
		// TODO Auto-generated method stub
		TipoDocumento tipo = new TipoDocumento();
		tipo.setEstado(registro.getEstadoDTO());
		tipo.setTipo(registro.getTipoDTO());
		repo.save(tipo);
	}

	@Override
	public void editarTipo(TipoDocumentoEditar editar) {
		// TODO Auto-generated method stub
		TipoDocumento tipo = new TipoDocumento();
		tipo.setId(editar.getIdDTO());
		tipo.setEstado(editar.getEstadoDTO());
		tipo.setTipo(editar.getTipoDTO());
		repo.saveAndFlush(tipo);
		
	}

	@Override
	public List<TipoDocumentoDTO> lisarTipos() {
		// TODO Auto-generated method stub
		TipoDocumentoDTO tipoDTO = null;
		List<TipoDocumentoDTO> tipoDTOList = new ArrayList<>();
		List<TipoDocumento> tipoList = repo.findAll();
		if(tipoList.size()!=0) {
			for (TipoDocumento tipo : tipoList) {
				tipoDTO = new TipoDocumentoDTO();
				tipoDTO.setEstadoDTO(tipo.getEstado());
				tipoDTO.setIdDTO(tipo.getId());
				tipoDTO.setTipoDTO(tipo.getTipo());
				tipoDTOList.add(tipoDTO);
			}
			
			
		}
		
		return tipoDTOList;
	}

	@Override
	public TipoDocumentoDTO buscarId(Integer id) {
		// TODO Auto-generated method stub
		TipoDocumentoDTO tipoDTO = new TipoDocumentoDTO();
		
		Optional<TipoDocumento> tipo = repo.findById(id);
		
		tipoDTO.setEstadoDTO(tipo.get().getEstado());
		tipoDTO.setIdDTO(tipo.get().getId());
		tipoDTO.setTipoDTO(tipo.get().getTipo());
		
		return tipoDTO;
	}

	@Override
	public void eliminarDocumento(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

}
