package idat.com.dto.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioEditar {
	
	@Positive
	@Digits(integer = 5, fraction = 0)
	@NotNull(message = "El campo idDTO no puede ser nulo")
	private Integer idDTO;
	
	//@NotBlank(message = "El campo nombreDTO no puede estar en blanco")
	private String nombreDTO;
	
	//@NotBlank(message = "El campo apPaternoDTO no puede estar en blanco")
	private String apPaternoDTO;
	
	//@NotBlank(message = "El campo apMaternoDTO no puede estar en blanco")
	private String apMaternoDTO;
	
	//@NotBlank(message = "El campo documentoDTO no puede estar en blanco")
	@Size(min = 6,max = 12,message = "Solo se acepta DNI o PASAPORTE")
	private String documentoDTO;
	
	
	@Size(min = 7,max = 9,message = "Ingreso mal su numero telefonico. Len 7 o 9")
	private String telefonoDTO;
	
	private String estadoDTO;
	
	//@NotBlank(message = "El campo emailDTO no puede estar en blanco")
	@Email
	private String emailDTO;
	
	@Size(min = 10,message = "La longitud minima de la contraseña es de 10")
	private String contrasenaDTO;
	
	private Integer idRolDTO;
	
	private Integer idEmpresaDTO;

	private Integer idTipoDocumento;
}
