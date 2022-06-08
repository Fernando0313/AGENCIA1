package idat.com.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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

import idat.com.dto.request.PaisDTORequest;
import idat.com.dto.response.PaisDTOResponse;
import idat.com.model.Pais;
import idat.com.service.PaisServiceImpl;

@RestController
@RequestMapping("/api/v1/pais")
@CrossOrigin(origins = "*")
public class PaisController {

	@Autowired
	private PaisServiceImpl serv;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object>  registrar(@RequestBody PaisDTORequest pais){
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _validacion = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<idat.com.dto.request.PaisDTORequest>> violations = validator.validate(pais);
			
			for (ConstraintViolation<PaisDTORequest> violation : violations) {
			    _validacion.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_validacion.isEmpty()) {
				errors.put("message", "error al registrar");
				errors.put("content", _validacion);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			Boolean bool = serv.existePais(pais.getNombreDTO());
			if(bool) {
				errors.put("message", "Error, este pais ya esta registrado");
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			PaisDTOResponse response = serv.guardarPais(pais);
			
			exito.put("message", "registrado correctamente");
			exito.put("content", response);
			return new ResponseEntity<>(exito,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			errors.put("content", null);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
	
	}
	
	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public ResponseEntity<Object> listar(){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			List<PaisDTOResponse> paisList = serv.listarPais();
			if(paisList.size()==0) {
				return new ResponseEntity<>(errors,HttpStatus.NO_CONTENT);
			}
			
			
			exito.put("content", paisList);
			exito.put("message", "Listado exitoso");
			return new ResponseEntity<>(exito,HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			errors.put("content", null);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable Integer id){

		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			Boolean bool = serv.existePaisId(id);
			
			if(!bool) {
				
				errors.put("message", "No existe este pais");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
			serv.eliminarPais(id);
		exito.put("message", "Eliminado exitosamente");
		return new ResponseEntity<Object>(exito,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			//rtn.put("content", e);
			return new ResponseEntity<>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(path = "/listar/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Object> listarPorId(@PathVariable Integer id){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {

Boolean bool = serv.existePaisId(id);
			
			if(!bool) {
				
				errors.put("message", "No existe este pais");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
			PaisDTOResponse _pais = serv.obtenerPais(id);
			exito.put("content", _pais);
			exito.put("message", "Pais encontrado");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editar(@RequestBody PaisDTORequest paisDTO){
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _validacion = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<idat.com.dto.request.PaisDTORequest>> violations = validator.validate(paisDTO);
			
			for (ConstraintViolation<PaisDTORequest> violation : violations) {
			    _validacion.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_validacion.isEmpty()) {
				errors.put("message", "error al actualizar");
				errors.put("content", _validacion);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}

				Boolean bool = serv.existePaisId(paisDTO.getIdPaisDTO());
			
			if(!bool) {
				
				errors.put("message", "No existe este pais");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
			
			
			PaisDTOResponse _p = serv.findByNombre(paisDTO.getNombreDTO());
			if(_p!=null&&_p.getIdPaisDTO()!=paisDTO.getIdPaisDTO()) {
				errors.put("message","Este nombre de pais ya esta registrado");
				return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
			}
				serv.editarPais(paisDTO);
				exito.put("content", paisDTO);
				exito.put("message", "Pais editado exitosamente");
			
				return new ResponseEntity<>(exito,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			//errors.put("message", e);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
	
}
