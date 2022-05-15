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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.com.dto.PaisDTORequest;
import idat.com.service.PaisServiceImpl;

@RestController
@RequestMapping("/v1/pais")
public class PaisController {

	@Autowired
	private PaisServiceImpl serv;
	
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object>  registrar(@RequestBody PaisDTORequest pais){
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> rtn = new LinkedHashMap<>();
		Set<ConstraintViolation<@Valid PaisDTORequest>> violations = validator.validate(pais);
		
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
		
		//serv.guardarPais(pais);
		
		
		
		rtn.put("message", "registrado correctamente");
		rtn.put("content", pais);
		return new ResponseEntity<>(rtn,HttpStatus.CREATED);
	}
}
