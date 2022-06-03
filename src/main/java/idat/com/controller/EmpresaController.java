package idat.com.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.com.dto.request.EmpresaEditar;
import idat.com.dto.request.EmpresaRegistrar;
import idat.com.dto.response.EmpresaDTO;
import idat.com.service.EmpresaServiceImpl;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaServiceImpl serv;

	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object>  registrar(@RequestBody EmpresaRegistrar empresaRegistrar){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _violation = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<EmpresaRegistrar>> violations = validator.validate(empresaRegistrar);
			
			for (ConstraintViolation<EmpresaRegistrar> violation : violations) {
				_violation.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			
			if(!_violation.isEmpty()) {
				errors.put("message", "error al registrar");
				errors.put("content", _violation);
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			Boolean bool = serv.existeEmpresa(empresaRegistrar.getNombreDTO());
			
			if(bool) {
				errors.put("message", "Error esta empresa ya existe");
				errors.put("content", null);
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.guardarEmpresa(empresaRegistrar);
			exito.put("message", "Rol registrado exitosamente");
			
			return new ResponseEntity<>(exito,HttpStatus.CREATED);
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error al registrar");
			return new ResponseEntity<>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public ResponseEntity<Object> listarRol(){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			List<EmpresaDTO> empresaList = serv.listarEmpresas();
			if(empresaList.size()==0) {
				
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
			
			exito.put("content", empresaList);
			exito.put("message", "Listado exitoso");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
		
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("messarge", "Error");
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable Integer id){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			
			Boolean bool = serv.existeId(id);
			if(!bool) {
				
				errors.put("message", "Error no se encontro este id de esta empresa");
				
				return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.eliminarEmpresa(id);
			exito.put("message", "Empresa eliminada exitosamente");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");			
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/listar/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Object> listarPorId(@PathVariable Integer id){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {

			
			Boolean bool = serv.existeId(id);
			
			if(!bool) {
				errors.put("message", "Error, no se encontro esta empresa");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			EmpresaDTO empresaDTO = serv.buscarId(id);

			exito.put("content",empresaDTO);
			exito.put("message", "Empresa encontrada");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editar(@RequestBody EmpresaEditar empresaEditar ){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _violation = new LinkedHashMap<>();
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<EmpresaEditar>> violations = validator.validate(empresaEditar );
			
			for (ConstraintViolation<EmpresaEditar> violation : violations) {
				_violation.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_violation.isEmpty()) {
				errors.put("message", "error al editar");
				errors.put("content", _violation);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			Boolean bool = serv.existeId(empresaEditar.getIdDTO());
			
			if(!bool) {
				errors.put("message", "Error, no se encontro esta empresa");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.editarEmpresa(empresaEditar);
			exito.put("message","Actualizacion exitosa");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			//errors.put("content", e);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
	}

}
