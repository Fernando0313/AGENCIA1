package idat.com.controller;

import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.com.dto.request.CiudadDTORequest;
import idat.com.dto.request.PaisDTORequest;
import idat.com.dto.response.CiudadDTOResponse;
import idat.com.dto.response.PaisDTOResponse;
import idat.com.model.Ciudad;
import idat.com.service.CiudadService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CiudadController {

	@Autowired
	private CiudadService serv;
	
	
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object> registrar(@RequestBody CiudadDTORequest ciudad){
		
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> rtn = new LinkedHashMap<>();
		Set<ConstraintViolation<idat.com.dto.request.CiudadDTORequest>> violations = validator.validate(ciudad);
		
		for (ConstraintViolation<CiudadDTORequest> violation : violations) {
		    System.out.print(violation.getPropertyPath()+"-"+violation.getMessage()); 
		    System.out.println("");
		    errors.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		if(!errors.isEmpty()) {
			rtn.put("message", "error al registrar");
			rtn.put("content", errors);
			
			return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
		}
		
		CiudadDTOResponse response = serv.guardarCiudad(ciudad);
		if(response.getNombreDTO()!=null ) {
			
			rtn.put("message", "registrado correctamente");
			rtn.put("content", response);
			return new ResponseEntity<>(rtn,HttpStatus.CREATED);
		}
		
		//;
		
		
		
		rtn.put("message", "Error");
		rtn.put("content", null);
		return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
	
	}
	
}
