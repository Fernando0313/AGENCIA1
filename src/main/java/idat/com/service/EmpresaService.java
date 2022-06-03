package idat.com.service;

import java.util.List;

import idat.com.dto.request.EmpresaEditar;
import idat.com.dto.request.EmpresaRegistrar;
import idat.com.dto.response.EmpresaDTO;

public interface EmpresaService {

	public Boolean existeId(Integer id);
	public Boolean existeEmpresa(String empresa);
	public void guardarEmpresa(EmpresaRegistrar empresaRegistrar);
	public void editarEmpresa(EmpresaEditar empresaEditar);
	public List<EmpresaDTO> listarEmpresas();
	public EmpresaDTO buscarId(Integer id);
	public void eliminarEmpresa(Integer id);
}
