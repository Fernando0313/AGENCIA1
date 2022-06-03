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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.com.dto.request.UsuarioEditar;
import idat.com.dto.request.UsuarioRegistro;
import idat.com.dto.response.UsuarioDTO;
import idat.com.dto.response.UsuarioLogin;
import idat.com.service.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl Userv;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@RequestMapping(path = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Object> registrarUsuario(@RequestBody UsuarioRegistro usuarioRegistro){
		
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> _validacion = new LinkedHashMap<>();
		try {	
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<UsuarioRegistro>> violations = validator.validate(usuarioRegistro);
			
			for (ConstraintViolation<UsuarioRegistro> violation : violations) {
			    _validacion.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_validacion.isEmpty()) {
				errors.put("message", "error al registrar");
				errors.put("content", _validacion);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			Boolean _documento = Userv.existDocumento(usuarioRegistro.getDocumentoDTO());
			Boolean _email = Userv.existEmail(usuarioRegistro.getEmailDTO());
			if(_documento) {
				errors.put("message", "Este documento ya esta registrado");
				errors.put("content", null);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			if(_email) {
				errors.put("message", "Este email ya esta registrado");
				errors.put("content", null);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			
			
			
			String contrasenaEncode = encoder.encode(usuarioRegistro.getContrasenaDTO());
			usuarioRegistro.setContrasenaDTO(contrasenaEncode);
			UsuarioDTO response = Userv.guardarUsuario(usuarioRegistro);
		
				
				exito.put("message", "registrado correctamente");
				exito.put("content", response);
				return new ResponseEntity<>(exito,HttpStatus.CREATED);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			errors.put("message", "Error");
			//exito.put("content", e);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}

	}
	


	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Object> editarUsuario(@RequestBody UsuarioEditar usuarioEditar){
		
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		Map<String, Object> _validacion = new LinkedHashMap<>();
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<UsuarioEditar>> violations = validator.validate(usuarioEditar);
			
			for (ConstraintViolation<UsuarioEditar> violation : violations) {
			    _validacion.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			if(!_validacion.isEmpty()) {
				errors.put("message", "Error al actualizar");
				errors.put("content", _validacion);
				
				return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
			}
			//if(usuarioEditar.getDocumentoDTO()!=null &&usuarioEditar.getEmailDTO()!=null) {
				Boolean bool = Userv.existsId(usuarioEditar.getIdDTO());
				if(!bool) {
					errors.put("message", "Esteusuario no existe");
					return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
				}
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
				if(usuarioEditar.getContrasenaDTO()!=null) {
					
				
					String contrasenaEncode = encoder.encode(usuarioEditar.getContrasenaDTO());
					usuarioEditar.setContrasenaDTO(contrasenaEncode);		  
				}
			UsuarioDTO usuarioDTO = Userv.editarUsuario(usuarioEditar);
			exito.put("message", "Actualizado correctamente");
			exito.put("content", usuarioDTO);
			return new ResponseEntity<>(exito,HttpStatus.OK);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			errors.put("message", "Error");
			errors.put("content", e);
			return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
		
	}

	
	@RequestMapping(path = "/listar/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Object> listarPorId(@PathVariable Integer id){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			UsuarioLogin usuarioLogin = Userv.buscarPorId(id);
			if(usuarioLogin==null) {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				errors.put("message", "No se encontro este usuario");
				return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
			}else {
				exito.put("message", "Usuario encontrado");
				exito.put("content",usuarioLogin);
				return new ResponseEntity<Object>(exito,HttpStatus.OK);
			}
		}catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			errors.put("content", e);
			return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/listar" , method = RequestMethod.GET)
	public ResponseEntity<Object> listarUsuarios(){
		Map<String, Object> errors = new LinkedHashMap<>();
		Map<String, Object> exito = new LinkedHashMap<>();
		try {
			List<UsuarioLogin> usuarioLogin = Userv.listarUsuarios();
			if(usuarioLogin.size()==0) {
			
				errors.put("message", "No hay usuarios registrados");
				return new ResponseEntity<Object>(errors,HttpStatus.NO_CONTENT);
			}else {
				exito.put("message", "Usuario encontrado");
				exito.put("content",usuarioLogin);
				return new ResponseEntity<Object>(exito,HttpStatus.OK);
			}
		}catch (Exception e) {
			// TODO: handle exception
			errors.put("message", "Error");
			errors.put("content", e);
			return new ResponseEntity<Object>(errors,HttpStatus.NOT_FOUND);
		}
	}
	
	

}
