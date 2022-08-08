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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import idat.com.dto.request.TipoDocumentoEditar;
import idat.com.dto.request.TipoDocumentoRegistro;
import idat.com.dto.response.TipoDocumentoDTO;
import idat.com.service.TipoDocumentoServiceImpl;

@RestController
@RequestMapping("/api/v1/tipoDocumento")
@CrossOrigin(origins = "*")
public class TipoDocumentoController {
	@Autowired
	private TipoDocumentoServiceImpl serv;

	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object>  registrar(@RequestBody TipoDocumentoRegistro registrar){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _violation = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<TipoDocumentoRegistro>> violations = validator.validate(registrar);
			
			for (ConstraintViolation<TipoDocumentoRegistro> violation : violations) {
				_violation.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			
			if(!_violation.isEmpty()) {
				errors.put("message", "Error al registrar");
				errors.put("content", _violation);
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			Boolean bool = serv.existeTipo(registrar.getTipoDTO());
			
			if(bool) {
				errors.put("message", "Error este tipo de documento ya existe");
				errors.put("content", null);
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.registrarTipo(registrar);
			exito.put("message", "Tipo documento registrado exitosamente");
			
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
			List<TipoDocumentoDTO> tipoDTOList = serv.lisarTipos();
			if(tipoDTOList.size()==0) {
				
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
			
			exito.put("content", tipoDTOList);
			exito.put("message", "Listado exitoso");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
		
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("messarge", "Error");
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable Integer id){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			
			Boolean bool = serv.existeId(id);
			if(!bool) {
				
				errors.put("message", "Error no se encontro este id de este tipo de documento");
				
				return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.eliminarDocumento(id);
			exito.put("message", "Documento eliminado exitosamente");
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
				errors.put("message", "Error, no se encontro este tipo de documento");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			TipoDocumentoDTO tipoDocumentoDTO = serv.buscarId(id);

			exito.put("content",tipoDocumentoDTO);
			exito.put("message", "Tipo de documento encontrado");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editar(@RequestBody TipoDocumentoEditar editar ){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _violation = new LinkedHashMap<>();
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<TipoDocumentoEditar>> violations = validator.validate(editar );
			
			for (ConstraintViolation<TipoDocumentoEditar> violation : violations) {
				_violation.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_violation.isEmpty()) {
				errors.put("message", "error al editar");
				errors.put("content", _violation);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			Boolean bool = serv.existeId(editar.getIdDTO());
			
			if(!bool) {
				errors.put("message", "Error, no se encontro este tipo de documento");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.editarTipo(editar);
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
