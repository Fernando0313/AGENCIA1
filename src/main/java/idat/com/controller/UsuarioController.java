package idat.com.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.com.dto.request.CiudadDTORequest;
import idat.com.dto.request.UsuarioEditar;
import idat.com.dto.request.UsuarioRegistro;
import idat.com.dto.response.UsuarioDTO;
import idat.com.dto.response.UsuarioLogin;
import idat.com.service.RolServiceImpl;
import idat.com.service.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl Userv;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	
	@Autowired
	private RolServiceImpl  Rserv;
	
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object> registrarUsuario(@RequestBody UsuarioRegistro usuarioRegistro){
		
		Map<String, Object> rtn = new LinkedHashMap<>();
		try {	
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Map<String, Object> errors = new LinkedHashMap<>();
			Set<ConstraintViolation<UsuarioRegistro>> violations = validator.validate(usuarioRegistro);
			
			for (ConstraintViolation<UsuarioRegistro> violation : violations) {
			    errors.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!errors.isEmpty()) {
				rtn.put("message", "error al registrar");
				rtn.put("content", errors);
				
				return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
			}
			
			Boolean _documento = Userv.existDocumento(usuarioRegistro.getDocumentoDTO());
			Boolean _email = Userv.existEmail(usuarioRegistro.getEmailDTO());
			if(_documento) {
				rtn.put("message", "Este documento ya esta registrado");
				rtn.put("content", null);
				
				return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
			}
			
			if(_email) {
				rtn.put("message", "Este email ya esta registrado");
				rtn.put("content", null);
				
				return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
			}
			
			
			
			String contrasenaEncode = encoder.encode(usuarioRegistro.getContrasenaDTO());
			usuarioRegistro.setContrasenaDTO(contrasenaEncode);
			UsuarioDTO response = Userv.guardarUsuario(usuarioRegistro);
			if(response.getNombre()!=null ) {
				
				rtn.put("message", "registrado correctamente");
				rtn.put("content", response);
				return new ResponseEntity<>(rtn,HttpStatus.CREATED);
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
	


	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editarUsuario(@RequestBody UsuarioEditar usuarioEditar){
		
		System.out.println(usuarioEditar.getEmailDTO());
		Map<String, Object> rtn = new LinkedHashMap<>();
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Map<String, Object> errors = new LinkedHashMap<>();
			Set<ConstraintViolation<UsuarioEditar>> violations = validator.validate(usuarioEditar);
			
			for (ConstraintViolation<UsuarioEditar> violation : violations) {
			    errors.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!errors.isEmpty()) {
				rtn.put("message", "Error al actualizar");
				rtn.put("content", errors);
				
				return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
			}
			//if(usuarioEditar.getDocumentoDTO()!=null &&usuarioEditar.getEmailDTO()!=null) {
				String documento = " ";
				String email = " ";
				
				if(usuarioEditar.getDocumentoDTO()!=null&&usuarioEditar.getEmailDTO()!=null) {
					UsuarioLogin usuarioDocument = Userv.buscarDocumento(usuarioEditar.getDocumentoDTO());
					UsuarioLogin usuarioEmail = Userv.buscarEmail(usuarioEditar.getEmailDTO());
					if(usuarioDocument!=null&& usuarioEmail!=null) {
						if(usuarioDocument.getIdDTO()!=usuarioEditar.getIdDTO() && usuarioDocument.getDocumentoDTO().equals(usuarioEditar.getDocumentoDTO())
								&& usuarioEmail.getIdDTO()!=usuarioEditar.getIdDTO() && usuarioEmail.getEmailDTO().equals(usuarioEditar.getEmailDTO()) ) {
														
														errors.put("message", "Este documento eh email ya esta reistrado con otro usuario");
														errors.put("content",null);
														return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
													
														}
					}
				}
				
				
				if(usuarioEditar.getDocumentoDTO()!=null) {
					UsuarioLogin usuarioDocument = Userv.buscarDocumento(usuarioEditar.getDocumentoDTO());
					if(usuarioDocument!=null) {
						if(usuarioDocument.getIdDTO()!=usuarioEditar.getIdDTO() && usuarioDocument.getDocumentoDTO().equals(usuarioEditar.getDocumentoDTO())) {
							
							errors.put("message", "Este documento  ya esta reistrado con otro usuario");
							errors.put("content",null);
							return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
						
							}
					}
					
				}
			
					if(usuarioEditar.getEmailDTO()!=null) {
						UsuarioLogin usuarioEmail = Userv.buscarEmail(usuarioEditar.getEmailDTO());
						
						if(usuarioEmail!=null) {
							if(usuarioEmail.getIdDTO()!=usuarioEditar.getIdDTO() && usuarioEmail.getEmailDTO().equals(usuarioEditar.getEmailDTO())) {
								
								errors.put("message", "Este email ya esta reistrado con otro usuario");
								errors.put("content",null);
								return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
							
								}
						}
					
					}
				
				
				
				
				
			
					
			
			//}
			
					  
			
			UsuarioDTO usuarioDTO = Userv.editarUsuario(usuarioEditar);
			rtn.put("message", "Actualizado correctamente");
			rtn.put("content", usuarioDTO);
			return new ResponseEntity<>(rtn,HttpStatus.OK);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			rtn.put("message", "Error");
			rtn.put("content", e);
			return new ResponseEntity<>(rtn,HttpStatus.NOT_FOUND);
		}
		
	}

	
	

}
