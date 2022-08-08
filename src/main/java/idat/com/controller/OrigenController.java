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

import idat.com.dto.request.OrigenEditar;
import idat.com.dto.request.OrigenRegistrar;
import idat.com.dto.request.PaisDTORequest;
import idat.com.dto.response.OrigenDTO;
import idat.com.dto.response.PaisDTOResponse;
import idat.com.model.Origen;
import idat.com.service.OrigenServiceImpl;
import idat.com.service.PaisServiceImpl;

@RestController
@RequestMapping("/api/v1/origen")
@CrossOrigin(origins = "*")
public class OrigenController {

	@Autowired
	private OrigenServiceImpl serv;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object>  registrar(@RequestBody OrigenRegistrar origenR){
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _validacion = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<OrigenRegistrar>> violations = validator.validate(origenR);
			
			for (ConstraintViolation<OrigenRegistrar> violation : violations) {
			    _validacion.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_validacion.isEmpty()) {
				errors.put("message", "error al registrar");
				errors.put("content", _validacion);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			Boolean bool = serv.existeOrigen(origenR.getNombreDTO());
			if(bool) {
				errors.put("message", "Error, este origen ya esta registrado");
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			serv.guardarOrigen(origenR);
			
			exito.put("message", "registrado correctamente");
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
		System.out.println("##########################");
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			List<Origen> origenList = serv.listarOrigen();
			if(origenList.size()==0) {
				
				return new ResponseEntity<>(errors,HttpStatus.NO_CONTENT);
			}
			System.out.println("##########################");
			System.out.println(origenList.get(0).getPais().getNombre());
			
			
			exito.put("content", origenList);
			exito.put("message", "Listado exitoso");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			errors.put("content", e);
			return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable Integer id){

		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			Boolean bool = serv.existeOrigenId(id);
			
			if(!bool) {
				
				errors.put("message", "No existe este origen");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
			serv.eliminarOrigen(id);
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

Boolean bool = serv.existeOrigenId(id);
			
			if(!bool) {
				
				errors.put("message", "No existe este origen");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
			OrigenDTO origenDTO = serv.obtenerOrigen(id);
			exito.put("content", origenDTO);
			exito.put("message", "Origen encontrado");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editar(@RequestBody OrigenEditar origenR ){
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _validacion = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<OrigenEditar>> violations = validator.validate(origenR);
			
			for (ConstraintViolation<OrigenEditar> violation : violations) {
			    _validacion.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_validacion.isEmpty()) {
				errors.put("message", "error al actualizar");
				errors.put("content", _validacion);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}

				Boolean bool = serv.existeOrigenId(origenR.getIdOrigenDTO());
			
			if(!bool) {
				
				errors.put("message", "No existe este pais");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
			
			
			OrigenDTO _o = serv.findByNombre(origenR.getNombreDTO());
			if(_o!=null&&_o.getIdOrigenDTO()!=origenR.getIdOrigenDTO()) {
				errors.put("message","Este nombre de origen ya esta registrado");
				return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
			}
				serv.editarOrigen(origenR);
				exito.put("message", "Origen editado exitosamente");
			
				return new ResponseEntity<>(exito,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			//errors.put("message", e);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
}
