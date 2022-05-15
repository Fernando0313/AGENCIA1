package idat.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.com.model.Ciudad;
import idat.com.service.CiudadService;


@RestController
@RequestMapping("/v1/ciudad")
public class CiudadController {

	@Autowired
	private CiudadService serv;
	
	
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Void> registrar(@RequestBody Ciudad ciudad){
		
		System.out.println(ciudad);
		serv.guardar(ciudad);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
}
