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
public class PaisController {

	@Autowired
	private PaisServiceImpl serv;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object>  registrar(@RequestBody PaisDTORequest pais){
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> rtn = new LinkedHashMap<>();
		Set<ConstraintViolation<idat.com.dto.request.PaisDTORequest>> violations = validator.validate(pais);
		
		for (ConstraintViolation<PaisDTORequest> violation : violations) {
		    System.out.print(violation.getPropertyPath()+"-"+violation.getMessage()); 
		    System.out.println("");
		    errors.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		if(!errors.isEmpty()) {
			rtn.put("message", "error al registrar");
			rtn.put("content", errors);
			
			return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
		}
		
		PaisDTOResponse response = serv.guardarPais(pais);
		if(response.getNombreDTO()!=null ) {
			
			rtn.put("message", "registrado correctamente");
			rtn.put("content", response);
			return new ResponseEntity<>(rtn,HttpStatus.CREATED);
		}
		rtn.put("message", "Error");
		rtn.put("content", null);
		return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public ResponseEntity<Object> listar(){
		
		List<Pais> lPais = new ArrayList<>();
		Map<String, Object> rtn = new LinkedHashMap<>();
		//serv.listarPais().forEach(lPais::add);
		/*
		for(Pais p : lPais) {
			System.out.println(p.getNombre()+"-"+p.getCiudad().size());
		}
		
		if(lPais.size()!=0) {
			rtn.put("message", "");
			rtn.put("content", lPais);
			return new ResponseEntity<>(rtn,HttpStatus.OK);
		}else if(lPais.size()==0) {
			rtn.put("message", "No data");
			rtn.put("content", null);
			return new ResponseEntity<>(rtn,HttpStatus.NO_CONTENT);

		}*/
		rtn.put("message", "Error");
		rtn.put("content",serv.listarPais());
		return new ResponseEntity<>(rtn,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable Integer id){
		Map<String, Object> rtn = new LinkedHashMap<>();
		try {
			
			PaisDTOResponse p = serv.obtenerPais(id);
			
			if(p!=null) {
				if(p.getCiudad().isEmpty()) {
					serv.eliminarPais(id);
					rtn.put("message", "Eliminado");
					return new ResponseEntity<>(rtn,HttpStatus.OK);
				}else {
					rtn.put("message", "Error, primero elimine sus relaciones");
					return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
				}

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			rtn.put("message", "Error");
			rtn.put("content", e);
			return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
		}
		rtn.put("message", "Error");
		return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(path = "/listar/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Object> listarPorId(@PathVariable Integer id){
		Map<String, Object> rtn = new LinkedHashMap<>();
		try {

			
			PaisDTOResponse _pais = serv.obtenerPais(id);
			
			if(_pais!=null) {
				rtn.put("message", "Pais encontrado");
				rtn.put("content", _pais);
				return new ResponseEntity<>(rtn,HttpStatus.OK);
			}else {
				rtn.put("message", "Error pais no encontrado");
				return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			rtn.put("message", "Error");
			rtn.put("message", e);
			return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editar(@RequestBody PaisDTORequest paisDTO){
		
		Map<String, Object> rtn = new LinkedHashMap<>();
		
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Map<String, Object> errors = new LinkedHashMap<>();
			
			Set<ConstraintViolation<idat.com.dto.request.PaisDTORequest>> violations = validator.validate(paisDTO);
			
			for (ConstraintViolation<PaisDTORequest> violation : violations) {
			    System.out.print(violation.getPropertyPath()+"-"+violation.getMessage()); 
			    System.out.println("");
			    errors.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!errors.isEmpty()) {
				rtn.put("message", "error al registrar");
				rtn.put("content", errors);
				
				return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
			}
			
			PaisDTOResponse p = serv.obtenerPais(paisDTO.getIdPaisDTO());
			
			if(p!=null) {
				serv.editarPais(paisDTO);
				rtn.put("message", "Pais editado exitosamente");
				rtn.put("content", paisDTO);
				return new ResponseEntity<>(rtn,HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			rtn.put("message", "Error");
			rtn.put("message", e);
			return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
		}
		rtn.put("message", "Error");
		return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
	}
	
	
}
