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

import idat.com.dto.request.RolEditar;
import idat.com.dto.request.RolRegistro;
import idat.com.dto.response.RolDTO;
import idat.com.service.RolServiceImpl;

@RestController
@RequestMapping("/api/v1/rol")
@CrossOrigin(origins = "*")
public class RolController {

	@Autowired
	private RolServiceImpl serv;

	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object>  registrar(@RequestBody RolRegistro rolRegistro){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _violation = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<RolRegistro>> violations = validator.validate(rolRegistro);
			
			for (ConstraintViolation<RolRegistro > violation : violations) {
				_violation.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			
			if(!_violation.isEmpty()) {
				errors.put("message", "error al registrar");
				errors.put("content", _violation);
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			Boolean bool = serv.existeRol(rolRegistro.getNombreDTO());
			
			if(bool) {
				errors.put("message", "Error este rol ya existe");
				errors.put("content", null);
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.guardarRol(rolRegistro);
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
			List<RolDTO> rolDTOList = serv.listarRoles();
			if(rolDTOList.size()==0) {
				
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
			exito.put("message", "Listado exitoso");
			exito.put("content", rolDTOList);
			
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
			
			RolDTO rolDTO = serv.obtenerRolId(id);
			if(rolDTO==null) {
				
				errors.put("message", "Error no se encontro este id de rol");
				
				return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.eliminarRol(id);
			exito.put("message", "rol eliminado exitosamente");
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

			
			RolDTO rolDTO = serv.obtenerRolId(id);
			
			if(rolDTO==null) {
				errors.put("message", "Error, no se encontro este rol");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			exito.put("message", "Rol encontrado");
			exito.put("content", rolDTO);
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editar(@RequestBody RolEditar rolEditar){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _violation = new LinkedHashMap<>();
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation< RolEditar>> violations = validator.validate(rolEditar);
			
			for (ConstraintViolation<RolEditar> violation : violations) {
				_violation.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_violation.isEmpty()) {
				errors.put("message", "error al registrar");
				errors.put("content", _violation);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			RolDTO rolDTO = serv.obtenerRolId(rolEditar.getIdDTO());
			
			if(rolDTO==null) {
				errors.put("message", "Error, el rol a eliminar no existe");
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			serv.editarRol(rolEditar);
			exito.put("message","Eliminado exitosamente");
			return new ResponseEntity<Object>(exito,HttpStatus.OK);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			//errors.put("content", e);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
	
}
