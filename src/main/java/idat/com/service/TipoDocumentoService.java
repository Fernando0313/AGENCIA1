package idat.com.service;

import java.util.List;

import idat.com.dto.request.TipoDocumentoEditar;
import idat.com.dto.request.TipoDocumentoRegistro;
import idat.com.dto.response.TipoDocumentoDTO;

public interface TipoDocumentoService {
	public Boolean existeId(Integer id);
	public Boolean existeTipo(String tipo);
	public void registrarTipo(TipoDocumentoRegistro registro);
	public void editarTipo(TipoDocumentoEditar editar);
	public List<TipoDocumentoDTO> lisarTipos();
	public TipoDocumentoDTO buscarId(Integer id);
	public void eliminarDocumento(Integer id);
}
